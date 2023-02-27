package pre14.stackoverflow.login.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginEntity {
    @Email
    private String email;
    @NotBlank
    private String password;
}
