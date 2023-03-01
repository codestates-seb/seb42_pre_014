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

//    public QuestionVote questionVoteUp(long questionId) {
//        String loginMemberEmail = SecurityContextHolder.getContext().getAuthentication().getName(); //토큰에서 유저확인.
//        Question question = questionService.findQuestion(questionId);
//        Member member = memberService.findMemberByEmail(loginMemberEmail);
//
//        QuestionVote questionVote = findQuestionVote(question, member); // 현재 상태값 불러오기
//        if(questionVote.getVoteStatus().equals(QuestionVote.VoteStatus.UP)){ //만약에 UP 이면
//            questionVote.setVoteStatus(QuestionVote.VoteStatus.NONE); // UP 취소로 동작 (NONE 으로 변경)
//            question.setVoteCount(question.getVoteCount() -1); // 게시글의 카운트수 -1
//        } else if (questionVote.getVoteStatus().equals(QuestionVote.VoteStatus.NONE)) { // NONE 상태면
//            questionVote.setVoteStatus(QuestionVote.VoteStatus.UP); // UP 으로 변경
//            question.setVoteCount(question.getVoteCount() +1); // 게시글 카운트 수 +1
//        } else { //down 상태면
//            questionVote.setVoteStatus(QuestionVote.VoteStatus.UP); // DOWN -> UP 으로 변경
//            question.setVoteCount(question.getVoteCount() +2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
//        }
//        return questionVoteRepository.save(questionVote);
//    }


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

//    public QuestionVote questionVoteDown(long questionId) {
//        String loginMemberEmail = SecurityContextHolder.getContext().getAuthentication().getName(); //토큰에서 유저확인
//        Question question = questionService.findQuestion(questionId);
//        Member member = memberService.findMemberByEmail(loginMemberEmail);
//
//        QuestionVote questionVote = findQuestionVote(question, member);
//        if(questionVote.getVoteStatus().equals(QuestionVote.VoteStatus.DOWN)){ //만약에 DOWN 이면
//            questionVote.setVoteStatus(QuestionVote.VoteStatus.NONE); // DOWN 취소로 동작 (NONE 으로 변경)
//            question.setVoteCount(question.getVoteCount() +1); // 게시글의 카운트수 +1
//        } else if (questionVote.getVoteStatus().equals(QuestionVote.VoteStatus.NONE)) { // NONE 상태면
//            questionVote.setVoteStatus(QuestionVote.VoteStatus.DOWN); // DOWN 으로 변경
//            question.setVoteCount(question.getVoteCount() -1); // 게시글 카운트 수 +1
//        } else { //down 상태면
//            questionVote.setVoteStatus(QuestionVote.VoteStatus.DOWN); // UP -> DOWN 으로 변경
//            question.setVoteCount(question.getVoteCount() -2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
//        }
//        return questionVoteRepository.save(questionVote);
//    }

    public Question findVerifiedQuestion(long id) {
        Optional<Question> optionalQuestion =
                questionRepository.findById(id);
        Question verifiedQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND)); //리펙토링 필요
        return verifiedQuestion;
    }

//    public QuestionVote findQuestionVote (Question question, Member member){
//        Optional<QuestionVote> findQuestionVote = questionVoteRepository.findByQuestionAndMember(question, member);
//        return findQuestionVote.orElseGet(()-> new QuestionVote(question, member, QuestionVote.VoteStatus.NONE));
//    }
}
