package pre14.stackoverflow.questions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
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

    @PostMapping
    public ResponseEntity<Void> createQuestion(@Valid @RequestBody QuestionDto questionDto) {
        Long id = questionService.createQuestion(questionDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{member-id}")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long memberId,
                                               @Valid @RequestBody QuestionDto questionDto) {
        questionService.updateQuestion(memberId, questionDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long memberId) {
        questionService.deleteQuestion(memberId);
        return ResponseEntity.noContent().build();
    }
}

