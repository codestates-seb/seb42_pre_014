package pre14.stackoverflow.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.entity.AnswerVote;
import pre14.stackoverflow.answer.repository.AnswerVoteRepository;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;
import pre14.stackoverflow.questions.repository.QuestionVoteRepository;
import pre14.stackoverflow.questions.service.QuestionService;

@Service
@RequiredArgsConstructor
public class AnswerVoteService {

    private final AnswerService answerService;
    private final MemberService memberService;
    private final AnswerVoteRepository answerVoteRepository;


    public Answer upVote(long answerId, long memberId) {

        Answer answer = answerService.findAnswer(answerId);
        Member member = memberService.findMember(memberId);

        AnswerVote answerVote = answerVoteRepository.findByAnswerAndMember(answer, member);

        if (answerVote == null) {
            answerVote = new AnswerVote();
            answerVote.setAnswer(answer);
            answerVote.setMember(member);
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.UP);
            answer.setVoteCount(answer.getVoteCount() + 1);
        }
        else if (answerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.DOWN) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.UP);
            answer.setVoteCount(answer.getVoteCount() + 2);
        }
        else if (answerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.UP) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.NONE);
            answer.setVoteCount(answer.getVoteCount() - 1);
        }
        else if (answerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.NONE) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.UP);
            answer.setVoteCount(answer.getVoteCount() + 1);
        }

        answerVoteRepository.save(answerVote);
        return answerService.findAnswer(answerId);
    }

    public Answer downVote(long answerId, long memberId) {

        Answer answer = answerService.findAnswer(answerId);
        Member member = memberService.findMember(memberId);

        AnswerVote answerVote = answerVoteRepository.findByAnswerAndMember(answer, member);

        if (answerVote == null) {
            answerVote = new AnswerVote();
            answerVote.setAnswer(answer);
            answerVote.setMember(member);
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.DOWN);
            answer.setVoteCount(answer.getVoteCount() - 1);
        }
        else if (answerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.DOWN) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.NONE);
            answer.setVoteCount(answer.getVoteCount() + 1);
        }
        else if (answerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.UP) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.DOWN);
            answer.setVoteCount(answer.getVoteCount() - 2);
        }
        else if (answerVote.getAnswerVoteStatus() == AnswerVote.AnswerVoteStatus.NONE) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.DOWN);
            answer.setVoteCount(answer.getVoteCount() - 1);
        }

        answerVoteRepository.save(answerVote);
        return answerService.findAnswer(answerId);
    }
}