package pre14.stackoverflow.questions.service;

import org.springframework.stereotype.Service;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.repository.QuestionRepository;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(QuestionDto questionDto) {
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}

