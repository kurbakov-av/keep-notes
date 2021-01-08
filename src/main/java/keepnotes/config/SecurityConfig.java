package keepnotes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> userService = new DefaultOAuth2UserService();

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher publisher) {
        return new DefaultAuthenticationEventPublisher(publisher);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(handling -> handling.authenticationEntryPoint(entryPoint()))
                .csrf(csrf -> csrf.ignoringAntMatchers("/api/**"))
                .authorizeRequests(req -> req.mvcMatchers(HttpMethod.GET, "/api/**").permitAll())
                .oauth2Login(login -> login
                            .defaultSuccessUrl("/dashboard").loginPage("/signin")
                            .userInfoEndpoint(endpoint -> endpoint.userService(userService)))
                .logout(logout -> logout.logoutUrl("/signout").logoutSuccessUrl("/"));
    }

    private AuthenticationEntryPoint entryPoint() {
        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> mapping = new LinkedHashMap<>();
        mapping.put(new AntPathRequestMatcher("/api/**"), new Http403ForbiddenEntryPoint());
        DelegatingAuthenticationEntryPoint result = new DelegatingAuthenticationEntryPoint(mapping);
        result.setDefaultEntryPoint(new LoginUrlAuthenticationEntryPoint("/signin"));

        return result;
    }
}
