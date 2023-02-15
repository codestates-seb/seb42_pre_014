package pre14.stackoverflow.questions;

import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setTitle(questionDto.getTitle());
        question.setBody(questionDto.getBody());
        question.setTags(questionDto.getTags());
        return questionRepository.save(question);
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public List<Question> searchQuestions(String keyword) {
        return questionRepository.findByTitleContainsIgnoreCase(keyword);
    }

    public Question updateQuestion(Long id, QuestionDto questionDto) {
        Question question = getQuestionById(id);
        question.setTitle(questionDto.getTitle());
        question.setBody(questionDto.getBody());
        question.setTags(questionDto.getTags());
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}

