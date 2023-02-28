package pre14.stackoverflow.answer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.answer.dto.AnswerDto;
import pre14.stackoverflow.answer.dto.AnswerVoteDto;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.mapper.AnswerMapper;
import pre14.stackoverflow.answer.service.AnswerService;
import pre14.stackoverflow.answer.service.AnswerVoteService;
import pre14.stackoverflow.globaldto.MultiResponseDto;
import pre14.stackoverflow.globaldto.SingleResponseDto;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.mapper.MemberMapper;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.mapper.QuestionMapper;
import pre14.stackoverflow.questions.repository.QuestionRepository;
import pre14.stackoverflow.questions.service.QuestionService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/answers")
@Slf4j
@RequiredArgsConstructor
@Validated
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;
    private final QuestionMapper questionMapper;
    private final AnswerVoteService answerVoteService;
    private final QuestionService questionService;
    private final MemberService memberService;
    private final MemberMapper memberMapper;


    //post
    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post answerPostDto) {

        Member member = memberService.findVerifiedMember(answerPostDto.getMemberId()); //1번방법
        Question question = questionService.findQuestionById(answerPostDto.getQuestionId());
        // ---------------------------------  2번방법
//        Member member = new Member();
//        member.setMemberId(answerPostDto.getMemberId());   // 값이 들어가긴하나 다시 데이터를 빼내줘야함
        // ==================================
        log.info(answerPostDto.toString());
        Answer postAnswer = mapper.answerPostDtoToAnswer(answerPostDto);
        postAnswer.setMember(member); //1번방법
        postAnswer.setQuestion(question);
        log.info(postAnswer.toString());

        Answer answer = answerService.createAnswer(postAnswer);
//        Answer answerMeth1 = answerService.findAnswer(answer.getAnswerId());  멤버 안나옴 처리해야함
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponse(answer)),
                HttpStatus.CREATED);
    }
    //patch
    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@Positive @PathVariable("answer-id") long answerId,
                                      @Valid @RequestBody AnswerDto.Patch answerPatchDto) {
        answerPatchDto.setAnswerId(answerId);
        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(answerPatchDto));
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponse(answer))
                , HttpStatus.OK);
    }

    //find
    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponse(answer)),
                HttpStatus.OK);
    }
    //finds
    @GetMapping
    public ResponseEntity getAnswers(@RequestParam("page") int page,
                                                              @RequestParam("size") int size) {
        Page<Answer> answerPages = answerService.findAnswers(page -1, size);

        List<Answer> answers = answerPages.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.answersToAnswerResponseDtos(answers), answerPages),
                HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // 답변 추천
    @PatchMapping("/{answer-id}/upvotes")
    public ResponseEntity upVoteAnswer(@Positive @PathVariable("answer-id") long voteId,
                                       @Valid @RequestBody AnswerVoteDto requestBody) {

        Answer votedAnswer = answerVoteService.upVote(voteId, mapper.answerVoteDtoToAnswerVote(requestBody));

        return new ResponseEntity<>(mapper.answerToAnswerResponse(votedAnswer), HttpStatus.OK);
    }

    // 답변 비추천
    @PatchMapping("/{answer-id}/downvotes")
    public ResponseEntity downVoteAnswer(@PathVariable("answer-id") long voteId,
                                         @Valid @RequestBody AnswerVoteDto requestBody) {
        Answer votedAnswer = answerVoteService.downVote(voteId, mapper.answerVoteDtoToAnswerVote(requestBody));

        return new ResponseEntity<>(mapper.answerToAnswerResponse(votedAnswer), HttpStatus.OK);
    }
}
