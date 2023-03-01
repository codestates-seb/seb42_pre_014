package pre14.stackoverflow.questions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;
import pre14.stackoverflow.member.repository.MemberRepository;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;
import pre14.stackoverflow.questions.repository.QuestionRepository;
import pre14.stackoverflow.questions.repository.QuestionVoteRepository;

import java.util.Optional;

@Service
public class QuestionVoteService {
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final QuestionVoteRepository questionVoteRepository;

    public QuestionVoteService(QuestionRepository questionRepository,
                               MemberRepository memberRepository,
                               QuestionVoteRepository questionVoteRepository) {
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
        this.questionVoteRepository = questionVoteRepository;
    }

    public Question upVote(long questionId, QuestionVote questionVote) {
        Question verifiedQuestion = findVerifiedQuestion(questionId);   // db 저장된 질문

        QuestionVote findQuestionVote = questionVoteRepository.findByQuestionAndMemberId(verifiedQuestion, questionVote.getMemberId());

        if (findQuestionVote == null) {
            findQuestionVote = new QuestionVote();
            findQuestionVote.setQuestion(verifiedQuestion);
            findQuestionVote.setMemberId(questionVote.getMemberId());
            findQuestionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.UP);
            verifiedQuestion.setVoteCount(verifiedQuestion.getVoteCount() + 1);
            questionVoteRepository.save(findQuestionVote);
            questionRepository.save(verifiedQuestion);
        }
        else if (findQuestionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.DOWN) {
            verifiedQuestion.setVoteCount(verifiedQuestion.getVoteCount() + 1);
            questionVoteRepository.delete(findQuestionVote);
            questionRepository.save(verifiedQuestion);
        }
        else if (findQuestionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.UP) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_VOTED);
        }

        return verifiedQuestion;
    }

    public Question downVote(long questionId, QuestionVote questionVote) {
        Question verifiedQuestion = findVerifiedQuestion(questionId);// db 저장된 답변

        QuestionVote findQuestionVote = questionVoteRepository.findByQuestionAndMemberId(verifiedQuestion, questionVote.getMemberId());

        if (findQuestionVote == null) {
            findQuestionVote = new QuestionVote();
            findQuestionVote.setQuestion(verifiedQuestion);
            findQuestionVote.setMemberId(questionVote.getMemberId());
            findQuestionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.DOWN);
            verifiedQuestion.setVoteCount(verifiedQuestion.getVoteCount() - 1);
            questionVoteRepository.save(findQuestionVote);
            questionRepository.save(verifiedQuestion);
        }
        else if (findQuestionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.UP) {
            verifiedQuestion.setVoteCount(verifiedQuestion.getVoteCount() - 1);
            questionVoteRepository.delete(findQuestionVote);
            questionRepository.save(verifiedQuestion);

        }
        else if (findQuestionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.DOWN) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_VOTED);
        }

        return verifiedQuestion;
    }

    public Question findVerifiedQuestion(long id) {
        Optional<Question> optionalQuestion =
                questionRepository.findById(id);
        Question verifiedQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND)); //리펙토링 필요
        return verifiedQuestion;
    }
}
