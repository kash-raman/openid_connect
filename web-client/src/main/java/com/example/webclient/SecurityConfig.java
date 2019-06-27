package com.example.webclient;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.IMPLICIT;

@EnableOAuth2Client
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .clientRegistrationRepository(clientRegistrationRepository())
                .redirectionEndpoint()
                .baseUri("/test*")
        ;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration registration = ClientRegistration.withRegistrationId("sample-web")
                .authorizationUri("http://localhost:8081/oauth/authorize")
                .clientSecret("secret")
                .tokenUri("http://localhost:8081/oauth/token")
                .scope("user_info")
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
