package net.group.warehouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER").and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.cors()
                .and()
                .csrf().disable();
    }

    private AuthenticationSuccessHandler successHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.getWriter().append("OK");
            httpServletResponse.setStatus(200);
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.getWriter().append("Authentication failure");
            httpServletResponse.setStatus(401);
        };
    }
}
