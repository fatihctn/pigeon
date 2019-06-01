package com.fatihctn.pigeon.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    Environment env;

    @GetMapping(value = "/")
    public String main(Model model) {
        model.addAttribute("message", "Hello World!");
        model.addAttribute("appName", env.getProperty("app-name"));
        return "hello/main";
    }

}
