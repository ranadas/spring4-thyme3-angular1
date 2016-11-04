package com.rdas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by x148128 on 04/11/2016.
 */
@Controller
public class PartialsController {

    @RequestMapping("/partials/welcome")
    public String showWelcomePartials(Model model) {
        model.addAttribute("name", "Apply click!!");
        return "partials/welcome";
    }

    @RequestMapping("/partials/form-page")
    public String showFormPagePartials() {
        return "partials/form-page";
    }

    @RequestMapping("/partials/success")
    public String showSuccessPartials() {
        return "partials/success";
    }

    @RequestMapping("/partials/error")
    public String showErrorPartials() {
        return "partials/error";
    }
}