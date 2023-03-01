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
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.dto.QuestionVoteDto;
import pre14.stackoverflow.questions.entity.Question;
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
    private final AnswerVoteService answerVoteService;
    private final AnswerMapper mapper;
    private final QuestionService questionService;
    private final MemberService memberService;

    //post
    @PostMapping
    public ResponseEntity<?> postAnswer(@Valid @RequestBody AnswerDto.Post answerPostDto) {

        Answer postAnswer = mapper.answerPostDtoToAnswer(answerPostDto);

        postAnswer.setMember(memberService.findVerifiedMember(answerPostDto.getMemberId()));
        postAnswer.setQuestion(questionService.findQuestion(answerPostDto.getQuestionId()));

        Answer answer = answerService.createAnswer(postAnswer);
        Answer answer1 = answerService.findAnswer(answer.getAnswerId());
        AnswerDto.InfoResponse response = mapper.answerToAnswerInfoResponse(answer1);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    //patch
    @PatchMapping("/{answer-id}")
    public ResponseEntity<?> patchAnswer(@Positive @PathVariable("answer-id") long answerId,
                                         @Valid @RequestBody AnswerDto.Patch answerPatchDto) {
        answerPatchDto.setAnswerId(answerId);


        Answer requestAnswer = mapper.answerPatchDtoToAnswer(answerPatchDto);
        Answer answer = answerService.updateAnswer(requestAnswer);
        AnswerDto.InfoResponse response = mapper.answerToAnswerInfoResponse(answer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //find
    @GetMapping("/{answer-id}")
    public ResponseEntity<?> getAnswer(@PathVariable("answer-id") long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        AnswerDto.InfoResponse response = mapper.answerToAnswerInfoResponse(answer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    //finds
    @GetMapping
    public ResponseEntity<?> getAnswers(@RequestParam("page") int page,
                                     @RequestParam("size") int size) {
        Page<Answer> answerPages = answerService.findAnswers(page -1, size);

        List<Answer> answers = answerPages.getContent();
        List<AnswerDto.InfoResponse> response = mapper.answersToAnswerInfoResponseDtos(answers);

        return new ResponseEntity<>(new MultiResponseDto<>(response, answerPages), HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{answer-id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{answer-id}/upvotes")
    public ResponseEntity<?> upVoteAnswer(@Positive @PathVariable("answer-id") long answerId,
                                         @Valid @RequestBody QuestionVoteDto requestBody) {

        Answer votedAnswer = answerVoteService.upVote(answerId, requestBody.getMemberId());
        AnswerDto.Response response = mapper.answerToAnswerResponse(votedAnswer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //답변 비추천
    @PostMapping("/{answer-id}/downvotes")
    public ResponseEntity<?> downVoteQuestion(@PathVariable("answer-id") long answerId,
                                           @Valid @RequestBody QuestionVoteDto requestBody) {
        Answer votedAnswer = answerVoteService.downVote(answerId, requestBody.getMemberId());
        AnswerDto.Response response = mapper.answerToAnswerResponse(votedAnswer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

}