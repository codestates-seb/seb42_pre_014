package pre14.stackoverflow.questions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.repository.QuestionRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.ToDoubleBiFunction;

@Service
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    public QuestionService(QuestionRepository questionRepository,
                           MemberService memberService) {
        this.questionRepository = questionRepository;
        this.memberService = memberService;
    }

    public Question createQuestion(Question question) { //질문 생성
//게시글 작성자가 회원이 맞는지 확인
        memberService.findMember(question.getMember().getMemberId());
        return questionRepository.save(question); // 게시글 생성
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = finVerifiedQuestion(question.getQuestionId());

        Optional.ofNullable(question.getQuestionStatus())
                .ifPresent(questionStatus -> findQuestion.setQuestionStatus(questionStatus));

        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(long questionId) {
        return finVerifiedQuestion(questionId);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Page<Question> findQuestions(int page, int size){
    return questionRepository.findAll(PageRequest.of(page,size,
            Sort.by("questionId").descending()));
    }

    private Question finVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }
    private void verifyQuestion(Question question){
    //해당게시글 회원이 존재하는지 확인 코드
    }
}
