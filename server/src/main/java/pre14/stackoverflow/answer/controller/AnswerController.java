package pre14.stackoverflow.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.answer.dto.AnswerPatchDto;
import pre14.stackoverflow.answer.dto.AnswerPostDto;
import pre14.stackoverflow.answer.dto.AnswerResponseDto;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.mapper.AnswerMapper;
import pre14.stackoverflow.answer.repository.AnswerRepository;
import pre14.stackoverflow.answer.service.AnswerService;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerMapper mapper;
    private final AnswerService answerService;
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final MemberService memberService;

    //post
    @PostMapping
    public ResponseEntity<AnswerResponseDto> postAnswer(@RequestBody AnswerPostDto answerPostDto) {
        Question questionById = questionService.findQuestionById(answerPostDto.getQuestionId());
        Member memberById = memberService.findVerifiedMember(answerPostDto.getMemberId());

        System.out.println(answerPostDto.toString());
        Answer answer = mapper.answerPostToAnswer(answerPostDto);

        answer.setQuestion(questionById);
        answer.setMember(memberById);

        System.out.println(answer.toString());
        Answer saveAnswer = answerService.createAnswer(answer);
        System.out.println(saveAnswer.toString());
        AnswerResponseDto response = mapper.answerToAnswerResponse(saveAnswer);
        System.out.println(response.toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //patch
    @PatchMapping("/{answer-id}")
    public ResponseEntity<AnswerResponseDto> patchAnswer(@RequestBody AnswerPatchDto answerPatchDto,
                                                         @PathVariable("answer-id") long answerId) {
        Answer answer = mapper.answerPatchToAnswer(answerPatchDto);
        answer.setAnswerId(answerId);

        Answer updateAnswer = answerService.updateAnswer(answer);

        AnswerResponseDto response = mapper.answerToAnswerResponse(updateAnswer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //find
    @GetMapping("/{answer-id}")
    public ResponseEntity<AnswerResponseDto> getAnswer(@PathVariable("answer-id") long answerId) {
        Answer findAnswer = answerService.findAnswer(answerId);

        AnswerResponseDto response = mapper.answerToAnswerResponse(findAnswer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //finds
    @GetMapping
    public ResponseEntity<List<AnswerResponseDto>> getAnswers() {
        List<Answer> answers = answerService.findAnswers();

        List<AnswerResponseDto> response = mapper.answersToAnswerResponseDto(answers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{answer-id}")
    public void deleteAnswer(@PathVariable("answer-id") long answerId) {
        answerService.deleteAnswer(answerId);
    }
}
