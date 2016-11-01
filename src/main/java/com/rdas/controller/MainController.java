package com.rdas.controller;

import com.rdas.exception.CustomGenericException;
import com.rdas.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"/hello", "/"}, method = RequestMethod.GET)
    public String hello(@RequestParam(value="name", defaultValue = "called withot empty name param") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

//    @ResponseBody
//    @RequestMapping(method = RequestMethod.POST, path = "angpost")
//    public ResponseEntity savePage(@RequestBody String formData) throws Exception {
//        System.out.println("-->> formData " + formData);
//        return new ResponseEntity("success", HttpStatus.OK);
//    }
//
    // The following request mapping is to demonstrate error handling using controller advice
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{type:.+}", method = RequestMethod.GET)
    public ModelAndView getPages(@PathVariable("type") String type) throws Exception {
        if ("error".equals(type)) {
            // go handleCustomException
            throw new CustomGenericException("E888", "This is custom message");
        } else if ("io-error".equals(type)) {
            // go handleAllException
            throw new IOException();
        } else {
            return new ModelAndView("hello").addObject("name", type);
        }

    }

    @RequestMapping(value = {"/authors/all"}, method = RequestMethod.GET)
    public String showAuthors(Model model) {
        List all = bookService.findAll();
        model.addAttribute("name", "Authors Listing");
        model.addAttribute("authors", all);
        return "hello";
    }
}
