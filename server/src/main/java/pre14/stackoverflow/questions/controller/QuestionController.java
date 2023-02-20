package pre14.stackoverflow.questions.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.globaldto.MultiResponseDto;
import pre14.stackoverflow.globaldto.SingleResponseDto;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.mapper.QuestionMapper;
import pre14.stackoverflow.questions.service.QuestionService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @PostMapping
    public ResponseEntity createQuestion(@RequestBody QuestionDto.Post questionPostDto) {
        Question question = questionMapper.questionPostToQuestion(questionPostDto);
        System.out.println(question.toString());
        Question createQuestion = questionService.createQuestion(question);
        System.out.println(createQuestion.toString());
        QuestionDto.QuestionResponseDto response = questionMapper.questionToQuestionResponse(createQuestion);
        System.out.println(response.toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity updateQuestion(@PathVariable("question-id")@Positive long questionId,
                                         @RequestBody QuestionDto.Patch questionPatchDto) {
    Question question = questionMapper.questionPatchDtoToQuestion(questionPatchDto);
    question.setQuestionId(questionId);

    Question updateQuestion = questionService.
            updateQuestion(question);

    return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllQuestions(@RequestParam("page") int page,
                                            @RequestParam("size") int size) {
        Page<Question> questionPages = questionService.findQuestions(page -1, size);
        List<Question> questions = questionPages.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(questionMapper.questionToQuestionResponseDtos(questions),questionPages),
                HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestionById(@PathVariable("question-id") @Positive long questionId) {
    Question question = questionService.findQuestion(questionId);

    return new ResponseEntity<>(
            new SingleResponseDto<>(questionMapper.questionToQuestionResponse(question)),
            HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("question-id") long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}

