package pre14.stackoverflow.member.dto;

import lombok.*;
import pre14.stackoverflow.member.entity.Member;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberDto {

    @Getter
    @Setter
    @RequiredArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Post{
        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "이름은 공백이 아니여야 합니다.")
        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        private String phone;

        @NotBlank(message = "패스워드를 입력해 주세요(최소 8자 최대 12자)")
        @Pattern(regexp = "[(a-zA-Z0-9)`~!@#\\$%\\^&*\\(\\)-_=\\+]{8,12}", message = "영문자와 숫자, !@#$%^&*()_+-=만 사용 가능합니다 ")
        private String password;


    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private  long memberId;

        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다")
        private String phone;

        private String password;

        private Member.MemberStatus memberStatus;

        public void setMemberId(long memberId){
            this.memberId=memberId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Response{
        private long memberId;
        private String email;
        private String name;
        private String phone;
        private List<String> roles;
//        private LocalDateTime createdAt;
//        private LocalDateTime modifiedAt;

        private Member.MemberStatus memberStatus;

        private long numberOfQuestions;             //회원 작성 질문 총 갯수
        private long numberOfAnswers;

        public String getMemberStatus(){
            return memberStatus.getStatus();
        }
    }


}
