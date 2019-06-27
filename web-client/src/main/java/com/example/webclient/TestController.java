package com.example.webclient;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {
    @GetMapping({"/test", "/"})
    public String hello() {
        String object = SecurityContextHolder.getContext().getAuthentication().getName();
        return "Hello World," + object;
    }

    @GetMapping("/oauth2/authorize/code/*")
    public String hell() {
        return "I'm here ";
    }


}
