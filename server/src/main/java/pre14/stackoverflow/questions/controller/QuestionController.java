package pre14.stackoverflow.questions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.mapper.QuestionMapper;
import pre14.stackoverflow.questions.service.QuestionService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    //    private final AnswerService answerService;
    //    private final AnswerMapper answerMapper;
    public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createQuestion(@Valid @RequestBody QuestionDto.Post questionPostDto) {
        Question question = questionService.createQuestion(questionMapper.questionPostDtoToQuestion(questionPostDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/questions/{question-id}")
                .buildAndExpand(question.getQuestionId()).toUri(); //questionid인가 memberid인가?
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long memberId,
                                               @Valid @RequestBody QuestionDto questionDto) {
        questionService.updateQuestion(memberId, questionDto);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<QuestionDto> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long memberId) {
        QuestionDto question = questionService.getQuestionById(memberId);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long memberId) {
        questionService.deleteQuestion(memberId);
        return ResponseEntity.noContent().build();
    }
}

