package com.rdas.controller;

import com.rdas.model.FormUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = {"/hello", "/"}, method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name", defaultValue = "called without empty name param") String name,
                        Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    //http://g00glen00b.be/validating-the-input-of-your-rest-api-with-spring/
    //https://www.onehippo.org/labs/creating-your-first-angularjs-app-with-hippo-cms.html
    //https://github.com/onehippo/demo-angular-hippo/blob/master/app/scripts/app.js
    @ResponseBody
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public ResponseEntity saveUser(@Validated @RequestBody FormUser formUser, BindingResult bindingResult) {
        System.out.println("\n\n in save user\n\n");
        System.out.println(formUser);
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity("Job BALLS", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity("Job DONE", HttpStatus.OK);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void onValidationException(MethodArgumentNotValidException ex, HttpServletResponse response) throws IOException {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder errorMessages = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorMessages.append(fieldError.getField());
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMessages.toString());
    }
}
