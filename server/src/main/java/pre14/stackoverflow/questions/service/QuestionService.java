package pre14.stackoverflow.questions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.service.AnswerService;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.repository.QuestionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 필수요소 생성자만 생성 final , @notnull
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;
    private final AnswerService answerService;

    public Question createQuestion(Question question) { //질문 생성

        return questionRepository.save(question); // 게시글 생성
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findQuestionById(question.getQuestionId());

        Optional.ofNullable(question.getTitle()).ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContents()).ifPresent(findQuestion::setContents);

        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(long questionId) {
        return findQuestionById(questionId);
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }

    // *** 질문 채택 ***
    public Question resolveQuestion(Long answerId) {

        Answer answer = answerService.searchAnswerById(answerId); // answer 찾아서
        answerService.acceptAnswer(answer.getAnswerId()); //채택 처리
        //Answer acceptAnswer = answerService.acceptAnswer(answerId); // 채택 처리

        Question findQuestion = findQuestionById(answer.getQuestion().getQuestionId()); // question 검증

        // 채택한 답변이 존재하면 질문도 채택 상태로 만듬
        for(Answer a : getEveryAnswers(answer.getQuestion().getQuestionId())){
            if(a.getAnswerStatus().equals(Answer.AnswerStatus.ACCEPTED))
                findQuestion.setQuestionStatus(Question.QuestionStatus.QUESTION_ANSWERED);
        }

        // 2개의 답변 채택 불가능하도록
        List<Answer> totalAnswers = getEveryAnswers(answer.getQuestion().getQuestionId());
        List<Enum> statusList = totalAnswers.stream().map(Answer::getAnswerStatus).collect(Collectors.toList());

        if(Collections.frequency(statusList, Answer.AnswerStatus.ACCEPTED) >= 2){
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_VOTED);
        }

        return questionRepository.save(findQuestion);
    }

    // ** 질문 Id로 해당 답변 list 가져옴
    public List<Answer> getEveryAnswers(Long questionId){
        Question findQuestion = findQuestionById(questionId);

        return findQuestion.getAnswers();
    }

    public Question findQuestionById(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        return optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }
}
