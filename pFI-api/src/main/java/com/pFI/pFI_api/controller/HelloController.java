package com.pFI.pFI_api.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping
    public String greet(HttpServletRequest request){
        return "Mock HomePage, SessionID: " + request.getSession().getId();
    }
}
