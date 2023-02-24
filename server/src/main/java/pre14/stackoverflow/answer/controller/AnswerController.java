package pre14.stackoverflow.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import pre14.stackoverflow.globaldto.MultiResponseDto;
import pre14.stackoverflow.questions.entity.Question;

import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerMapper mapper;
    private final AnswerService answerService;
    private final AnswerRepository answerRepository;

    //post
    @PostMapping
    public ResponseEntity<AnswerResponseDto> postAnswer(@RequestBody AnswerPostDto answerPostDto) {

        Answer answer = mapper.answerPostToAnswer(answerPostDto);
        Answer saveAnswer = answerService.createAnswer(answer);

        AnswerResponseDto response = mapper.answerToAnswerResponse(saveAnswer);


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
    public ResponseEntity getAnswers(@RequestParam("page") int page,
                                                              @RequestParam("size") int size) {
        Page<Answer> answerPages = answerService.findAnswers(page -1, size);

        List<Answer> answers = answerPages.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.answersToAnswerResponseDto(answers), answerPages),
                HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{answer-id}")
    public void deleteAnswer(@PathVariable("answer-id") long answerId) {
        answerService.deleteAnswer(answerId);
    }
}
