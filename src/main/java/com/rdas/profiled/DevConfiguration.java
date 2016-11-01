package com.rdas.profiled;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * Created by rdas on 04/10/2016.
 */
@Profile("dev")
@Configuration
public class DevConfiguration {
    @PostConstruct
    public void init () {
        System.out.println(" \n\n ---- > In Dev Construct \n\n");
    }
}
