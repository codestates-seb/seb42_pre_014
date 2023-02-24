package pre14.stackoverflow.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pre14.stackoverflow.member.entity.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class MemberDto {

    @Getter
    @RequiredArgsConstructor
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
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private  long memberId;

        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다")
        private String phone;

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
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        private Member.MemberStatus memberStatus;

        public String getMemberStatus(){
            return memberStatus.getStatus();
        }
    }
}