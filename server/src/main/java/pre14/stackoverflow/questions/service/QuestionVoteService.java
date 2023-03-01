package pre14.stackoverflow.questions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.repository.MemberRepository;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;
import pre14.stackoverflow.questions.repository.QuestionRepository;
import pre14.stackoverflow.questions.repository.QuestionVoteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionVoteService {

    private final QuestionService questionService;
    private final MemberService memberService;
    private final QuestionVoteRepository questionVoteRepository;


    public Question upVote(long questionId, long memberId) {

        Question question = questionService.findQuestion(questionId);
        Member member = memberService.findMember(memberId);

        QuestionVote questionVote = questionVoteRepository.findByQuestionAndMember(question, member);

        if (questionVote == null) {
            questionVote = new QuestionVote();
            questionVote.setQuestion(question);
            questionVote.setMember(member);
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.UP);
            question.setVoteCount(question.getVoteCount() + 1);
        }
        else if (questionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.DOWN) {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.UP);
            question.setVoteCount(question.getVoteCount() + 2);
        }
        else if (questionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.UP) {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.NONE);
            question.setVoteCount(question.getVoteCount() - 1);
        }
        else if (questionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.NONE) {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.UP);
            question.setVoteCount(question.getVoteCount() + 1);
        }

        questionVoteRepository.save(questionVote);
        return questionService.findQuestion(questionId);
    }

    public Question downVote(long questionId, long memberId) {

        Question question = questionService.findQuestion(questionId);
        Member member = memberService.findMember(memberId);

        QuestionVote questionVote = questionVoteRepository.findByQuestionAndMember(question, member);

        if (questionVote == null) {
            questionVote = new QuestionVote();
            questionVote.setQuestion(question);
            questionVote.setMember(member);
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.DOWN);
            question.setVoteCount(question.getVoteCount() - 1);
        }
        else if (questionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.DOWN) {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.NONE);
            question.setVoteCount(question.getVoteCount() + 1);
        }
        else if (questionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.UP) {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.DOWN);
            question.setVoteCount(question.getVoteCount() - 2);
        }
        else if (questionVote.getQuestionVoteStatus() == QuestionVote.QuestionVoteStatus.NONE) {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.DOWN);
            question.setVoteCount(question.getVoteCount() - 1);
        }

        questionVoteRepository.save(questionVote);
        return questionService.findQuestion(questionId);
    }
}



//    public QuestionVote findQuestionVote (Question question, Member member){
//        Optional<QuestionVote> findQuestionVote = questionVoteRepository.findByQuestionAndMember(question, member);
//        return findQuestionVote.orElseGet(()-> new QuestionVote(question, member, QuestionVote.VoteStatus.NONE));
//    }
