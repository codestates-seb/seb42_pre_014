package pre14.stackoverflow.questions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.repository.QuestionRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleBiFunction;

@Service
@RequiredArgsConstructor // 필수요소 생성자만 생성 final , @notnull
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question) { //질문 생성
        return questionRepository.save(question); // 게시글 생성
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findQuestionById(question.getQuestionId());

        Optional.ofNullable(question.getTitle()).ifPresent(findQuestion :: setTitle);
        Optional.ofNullable(question.getContents()).ifPresent(findQuestion :: setContents);

        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(long questionId) {
        return findQuestionById(questionId);
    }

    public List<Question> findQuestions(){
        return questionRepository.findAll();
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public Page<Question> findQuestions(int page, int size){
    return questionRepository.findAll(PageRequest.of(page,size,
            Sort.by("questionId").descending()));
    }

    private Question findQuestionById(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }
}
