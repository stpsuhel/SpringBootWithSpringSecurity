package com.priyangona.priangona_backend.auth.controller;

import com.priyangona.priangona_backend.auth.UserPrinciple;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserDetailsService userService;

    @GetMapping("")
    public String getUser() {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(userPrinciple);
        return "adminHeader";
    }
}
