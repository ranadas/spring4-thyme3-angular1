package com.rdas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * Created by rdas on 16/10/2016.
 */
@RestController
public class ControllerMappingsController {
    private Logger log = LoggerFactory.getLogger(ControllerMappingsController.class);
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping(value = "/mappings", method = RequestMethod.GET)
    public ResponseEntity showMappings() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo key = entry.getKey();
            HandlerMethod value = entry.getValue();
            log.info("{}  == {}", key.toString(), value.toString());
        }
//        model.addAttribute("endPoints", handlerMethods.keySet());
//        return "mappings";
        return new ResponseEntity(handlerMethods.toString(), HttpStatus.OK);
    }
}
