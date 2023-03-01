package pre14.stackoverflow.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.entity.AnswerVote;
import pre14.stackoverflow.answer.repository.AnswerRepository;
import pre14.stackoverflow.answer.repository.AnswerVoteRepository;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;
import pre14.stackoverflow.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerVoteService {
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;
    private final AnswerVoteRepository answerVoteRepository;

    public Answer upVote(long answerId, AnswerVote answerVote) {
        Answer verifiedAnswer = findVerifiedAnswer(answerId);
        AnswerVote findAnswerVote = answerVoteRepository.findByAnswerAndMemberId(verifiedAnswer, answerVote.getMemberId());

        if (findAnswerVote == null) {
            findAnswerVote = new AnswerVote();
            findAnswerVote.setAnswer(verifiedAnswer);
            findAnswerVote.setMemberId(answerVote.getMemberId());
            findAnswerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.UP);
            verifiedAnswer.setVoteCount(verifiedAnswer.getVoteCount() + 1);
            answerVoteRepository.save(findAnswerVote);
            answerRepository.save(verifiedAnswer);
        }
        else if (findAnswerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.DOWN) {
            verifiedAnswer.setVoteCount(verifiedAnswer.getVoteCount() + 1);
            answerVoteRepository.delete(findAnswerVote);
            answerRepository.save(verifiedAnswer);
        }
        else if (findAnswerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.UP) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_VOTED);
        }

        return verifiedAnswer;
    }

    public Answer downVote(long answerId, AnswerVote answerVote) {
        Answer verifiedAnswer = findVerifiedAnswer(answerId);

        AnswerVote findAnswerVote = answerVoteRepository.findByAnswerAndMemberId(verifiedAnswer, answerVote.getMemberId());

        if (findAnswerVote == null) {
            findAnswerVote = new AnswerVote();
            findAnswerVote.setAnswer(verifiedAnswer);
            findAnswerVote.setMemberId(answerVote.getMemberId());
            findAnswerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.DOWN);
            verifiedAnswer.setVoteCount(verifiedAnswer.getVoteCount() - 1);
            answerVoteRepository.save(findAnswerVote);
            answerRepository.save(verifiedAnswer);
        }
        else if (findAnswerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.UP) {
            verifiedAnswer.setVoteCount(verifiedAnswer.getVoteCount() - 1);
            answerVoteRepository.delete(findAnswerVote);
            answerRepository.save(verifiedAnswer);
        }
        else if (findAnswerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.DOWN) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_VOTED);
        }

        return verifiedAnswer;
    }


    public Answer findVerifiedAnswer(long Id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(Id);
        Answer verifiedAnswer = optionalAnswer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return verifiedAnswer;
    }
}
