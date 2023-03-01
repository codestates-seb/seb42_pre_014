package pre14.stackoverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StackoverflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(StackoverflowApplication.class, args);
	}
}

// 전체 조회 가능 할 수 있게 생각 해보기, answer를 볼 때 member만 조회가 가능한지 알 수 있나?  /{answer} -> 참고

// 답변을 등록했을때 -> [댓글정보 + [답변자 회원정보]] OK
// 답변을 수정했을때 -> [댓글정보 + [답변자 회원정보]] OK

// 질문을 등록했을떄 ->          [질문자 회원정보][질문 내용 ] [댓글 갯수]    OK
// 질문을 수정했을때 ->          [질문자 회원정보][질문 내용] [댓글 갯수] 	 OK
// 질문을 한건 조회했을때 ->      [질문자 회원정보][질문 내용]          +  [댓글정보 + [답변자 회원정보]] OK  	DetailPageResponse   OK
// 질문 리스트페이지 조회했을때 -> [질문자 회원정보][질문 내용] [댓글 갯수]   								TotalPageResponse  OK

// 회원 등록시     [회원정보 총 게시물 갯수, 총 댓글갯수] OK
// 회원 수정시 ->  [회원정보 총 게시물 갯수, 총 댓글갯수] OK
// 회원 정보조회시  [회원정보 총 게시물 갯수, 총 댓글갯수] OK
// 내가쓴글 조회 ->
// 내가쓴댓글 조회 ->
/**
 *                 "numberOfQuestions": 0,
 *                 "numberOfAnswers": 0
 *
 * 질문의 답변갯수
 *            "answerCount": 0
 *
 *            1개 조회시 답변 안옴 			  OK
 *            답변에 questionId 값 안들어감   OK
 */
