package com.clonect.clothes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){ // model 이 컨트롤러를 통해 view로 데이터를 넘김
        model.addAttribute("data", "Hello!!");
        return "hello";
    }

}
