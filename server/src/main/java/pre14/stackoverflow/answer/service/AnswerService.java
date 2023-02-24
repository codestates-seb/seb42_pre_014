package pre14.stackoverflow.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.repository.AnswerRepository;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {

        Answer createdAnswer = answerRepository.save(answer);
        return createdAnswer;
    }

    public Answer updateAnswer(Answer answer) {
        Answer update = searchAnswerById(answer.getAnswerId());

        Optional.ofNullable(answer.getUserName()).ifPresent(update :: setUserName);
        Optional.ofNullable(answer.getContents()).ifPresent(update :: setContents);

        Answer updatedAnswer = answerRepository.save(update);
        return updatedAnswer;
    }

    public Answer findAnswer(long answerId) {
        Answer findedAnswer = searchAnswerById(answerId);
        return findedAnswer;
    }

    public List<Answer> findAnswers() {
        List<Answer> answers = answerRepository.findAll();
        return answers;
    }

    public void deleteAnswer(long answerId) {
        answerRepository.deleteById(answerId);
    }

    private Answer searchAnswerById(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer answer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return answer;
    }
}
