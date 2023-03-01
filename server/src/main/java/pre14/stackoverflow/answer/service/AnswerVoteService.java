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

}