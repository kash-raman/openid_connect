package com.example.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE;

@EnableOAuth2Client
@Configuration
public class AuthCodeSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .logout().addLogoutHandler(logouthandler())
                .and()
                .oauth2Login()
                .clientRegistrationRepository(clientRegistrationRepository())
                .redirectionEndpoint()
                .baseUri("/test*")


        ;
    }

    private LogoutHandler logouthandler() {

        return new LogoutHandler() {
            @Override
            public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                try {
                    response.sendRedirect("http://localhost:8081/logout");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration registration = ClientRegistration.withRegistrationId("sample-web")
                .authorizationUri("http://localhost:8081/oauth/authorize")
                .clientSecret("secret")
                .tokenUri("http://localhost:8081/oauth/token")
                .scope("default")
                .clientId("sample-web")
                .clientName("sample-web")
                .authorizationGrantType(AUTHORIZATION_CODE)
                .userInfoUri("http://localhost:8081/user/me")
                .userNameAttributeName("username")
                .redirectUriTemplate("{baseUrl}/test")
                .build();

        return new InMemoryClientRegistrationRepository(Arrays.asList(registration));
    }


}
