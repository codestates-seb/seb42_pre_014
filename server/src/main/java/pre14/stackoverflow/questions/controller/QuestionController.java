package pre14.stackoverflow.questions.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.service.AnswerService;
import pre14.stackoverflow.globaldto.MultiResponseDto;
import pre14.stackoverflow.globaldto.SingleResponseDto;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.repository.MemberRepository;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.dto.QuestionVoteDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.mapper.QuestionMapper;
import pre14.stackoverflow.questions.service.QuestionService;
import pre14.stackoverflow.questions.service.QuestionVoteService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
@Validated
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final MemberService memberService;
    private final QuestionVoteService questionVoteService;
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity postQuestion(@RequestBody @Valid QuestionDto.Post questionPostDto) {
        Member member = memberService.findVerifiedMember(questionPostDto.getMemberId());// 멤버를저장해주는
        Question question = questionMapper.questionPostToQuestion(questionPostDto);
        question.setMember(member); // 서비스로 옮기려했지만 실패

        Question createQuestion = questionService.createQuestion(question);

        QuestionDto.TotalPageResponse response = questionMapper.questionToQuestionTotalPageResponse(createQuestion);
        //토탈페이지
        
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.CREATED);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity updateQuestion(@PathVariable("question-id")@Positive long questionId,
                                         @Valid @RequestBody QuestionDto.Patch questionPatchDto) {

        Question question = questionMapper.questionPatchDtoToQuestion(questionPatchDto);
        question.setQuestionId(questionId);

        Question updateQuestion = questionService.updateQuestion(question);
        QuestionDto.TotalPageResponse response = questionMapper.questionToQuestionTotalPageResponse(updateQuestion);


        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    /**
     *  질문 전체조회 (질문 리스트페이지)
     */
    @GetMapping
    public ResponseEntity getAllQuestions(@RequestParam("page") int page, @RequestParam("size") int size) {

        Page<Question> questionPages = questionService.findQuestions(page -1, size);
        List<Question> questions = questionPages.getContent();

        List<QuestionDto.TotalPageResponse> response = questionMapper.questionToQuestionTotalPageResponseDtos(questions);

        return new ResponseEntity<>(new MultiResponseDto<>(response,questionPages), HttpStatus.OK);
    }

    /**
     * 질문 한건 조회 (질문 상세페이지, response에 답변 정보 포함)
     */
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestionById(@PathVariable("question-id") @Positive long questionId) {

        Question question = questionService.findQuestion(questionId);
        QuestionDto.DetailPageResponse response = questionMapper.questionToQuestionDetailPageResponse(question);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    /**
     * 질문 삭제
     */
    @DeleteMapping("/{question-id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("question-id") long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }



    //  답변 추천
    @PatchMapping("/{question-id}/upvotes")
    public ResponseEntity upVoteQuestion(@Positive @PathVariable("question-id") long id,
                                         @Valid @RequestBody QuestionVoteDto requestBody) {

        Question votedQuestion = questionVoteService.upVote(id, questionMapper.questionVoteDtoToQuestionVote(requestBody));

        return new ResponseEntity<>(questionMapper.questionToQuestionResponse(votedQuestion), HttpStatus.OK);
    }

    //답변 비추천
    @PatchMapping("/{question-id}/downvotes")
    public ResponseEntity downVoteQuestion(@PathVariable("question-id") long id,
                                           @Valid @RequestBody QuestionVoteDto requestBody) {
        Question votedQuestion = questionVoteService.downVote(id, questionMapper.questionVoteDtoToQuestionVote(requestBody));

        return new ResponseEntity<>(questionMapper.questionToQuestionResponse(votedQuestion), HttpStatus.OK);
    }
}