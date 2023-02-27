package pre14.stackoverflow.member.dto;

import lombok.Getter;
import pre14.stackoverflow.member.entity.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
public class LoginDto {
    @Email
    private String username;
    @Pattern(regexp = "^(?=.[a-zA-Z])(?=.[!@#$%^+=-])(?=.[0-9]).{8,12}$")
    private String password;


}
