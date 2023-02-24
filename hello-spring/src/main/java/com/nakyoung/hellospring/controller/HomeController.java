package com.nakyoung.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //"/" : localhost:8080 으로 들어오면 나오는 것
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
