package com.rdas.config;

import com.rdas.exception.CustomGenericException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rdas on 15/10/2016.
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView handleCustomException(CustomGenericException ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errCode", ex.getErrCode());
        model.addObject("errMsg", ex.getErrMsg());
        model.addObject("errorMessage", "This can't possible be empty");
        model.setViewName("error/generic_error");
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", "this is Exception.class");
        model.addObject("errorMessage", "This can't possible be empty2");
        model.setViewName("error/generic_error");
        return model;
    }
//    /*
//    have a global init binder
//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
//        webDataBinder.setBindEmptyMultipartFiles(false);
//    }
//     */
}
