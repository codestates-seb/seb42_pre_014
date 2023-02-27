package pre14.stackoverflow.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.login.entity.LoginEntity;
import pre14.stackoverflow.login.service.LoginService;
import pre14.stackoverflow.member.dto.LoginDto;
import pre14.stackoverflow.member.entity.Member;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/members/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public String login(@Valid @RequestBody LoginEntity login, BindingResult bindingResult) {
        Member loginMember = loginService.login(login.getEmail(), login.getPassword());
        if(loginMember == null) {
            bindingResult.reject("loginFail", "이메일 또는 비밀번호가 틀립니다.");
            return "Login Failed";
        }
        else {
            return "Login Success";
        }
    }


}