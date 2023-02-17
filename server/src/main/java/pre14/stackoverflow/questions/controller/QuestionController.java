package pre14.stackoverflow.questions.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pre14.stackoverflow.answer.mapper.AnswerMapper;
import pre14.stackoverflow.answer.service.AnswerService;
import pre14.stackoverflow.dto.MultiResponseDto;
import pre14.stackoverflow.dto.SingleResponseDto;
import pre14.stackoverflow.member.utils.UriCreator;
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
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    public QuestionController(QuestionService questionService,
                              QuestionMapper questionMapper,
                              AnswerService answerService,
                              AnswerMapper answerMapper) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }

    @PostMapping
    public ResponseEntity createQuestion(@Valid @RequestBody QuestionDto.Post requestBody) {
        Question question = questionMapper.questionPostDtoToQuestion(requestBody);
        Question createQuestion = questionService.createQuestion(question);

        URI location = UriCreator.createUri("/question",createQuestion.getQuestionId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity updateQuestion(@PathVariable("question-id")@Positive Long questionId,
                                               @Valid @RequestBody QuestionDto.Patch questionPatchDto) {
    questionPatchDto.setQuestionId(questionId);
    Question question = questionService.
            updateQuestion(questionMapper.questionPatchDtoToQuestion(questionPatchDto));

    return new ResponseEntity<>(
        new SingleResponseDto<>(questionMapper.questionToQuestionResponseDto(question))
            ,HttpStatus.OK);
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
    public ResponseEntity getQuestionById(@PathVariable("question-id") @Positive Long questionId) {
    Question question = questionService.findQuestion(questionId);

    return new ResponseEntity<>(
            new SingleResponseDto<>(questionMapper.questionToQuestionResponseDto(question)),
            HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}

