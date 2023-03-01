package pre14.stackoverflow.auth.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AuthDto {

    @Getter
    static public class Login {
        @Email
        private String email;
        @NotBlank
        private String password;
    }

    @Getter
    static public class Logout {
        @NotBlank
        private String accessToken;

        @NotBlank
        private String refreshToken;
    }
}
