package pre14.stackoverflow.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.repository.AnswerRepository;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {

        Answer findAnswer = searchAnswerById(answer.getAnswerId());
        // 답변 내용 업데이트
        Optional.ofNullable(answer.getContents())
                .ifPresent(contents -> findAnswer.setContents(contents));

        return answerRepository.save(findAnswer);
    }

    public Answer findAnswer(long answerId) {
        return searchAnswerById(answerId);
    }

    public Page<Answer> findAnswers(int page, int size) {
        return answerRepository.findAll(PageRequest.of(page, size,
                Sort.by("answerId").descending()));
    }

    public void deleteAnswer(long answerId) {
        answerRepository.deleteById(answerId);
    }


    public Answer searchAnswerById(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer answer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return answer;
    }

    @Transactional
    public Answer acceptAnswer(long answerId) {
        Answer findAnswer = searchAnswerById(answerId);

        // 채택한 answer status를 바꿈
        findAnswer.setAnswerStatus(Answer.AnswerStatus.ACCEPTED);

        // 바꾼 status로 저장
        return answerRepository.save(findAnswer);
    }
}