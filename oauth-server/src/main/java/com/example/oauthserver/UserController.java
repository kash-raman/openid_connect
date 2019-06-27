package com.example.oauthserver;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping("/user/me")
    public Credentials user(Principal principal) {
        Credentials actualPrincipal = (Credentials) ((OAuth2Authentication) principal).getUserAuthentication().getPrincipal();
        System.out.println(actualPrincipal);
        return actualPrincipal;
    }
}
