package com.example.oauthserver;

import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping("/user/me")
    public Credentials user(Principal principal) {
        Credentials actualPrincipal = (Credentials) ((OAuth2Authentication) principal).getUserAuthentication().getPrincipal();
        System.out.println(actualPrincipal);
        return actualPrincipal;
    }


    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenStore tokenStore;

    @GetMapping("/logout")
    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revokeToken() {
        final OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder
                .getContext().getAuthentication();
        final String token = tokenStore.getAccessToken(auth).getValue();
        tokenServices.revokeToken(token);
    }
}
