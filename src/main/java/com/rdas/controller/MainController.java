package com.rdas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping(value = {"/hello", "/"}, method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name", defaultValue = "called without empty name param") String name,
                        Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
