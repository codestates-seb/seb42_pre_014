package pre14.stackoverflow.auth.filter;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class JwtLogoutFilter extends LogoutFilter {

    public JwtLogoutFilter(LogoutSuccessHandler logoutSuccessHandler,
                           LogoutHandler... handlers) {

        super(logoutSuccessHandler, handlers);
        setFilterProcessesUrl("/members/logout");
    }
}

