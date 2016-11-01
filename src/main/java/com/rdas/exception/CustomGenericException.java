package com.rdas.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by rdas on 15/10/2016.
 */
@Getter @Setter @AllArgsConstructor
public class CustomGenericException extends RuntimeException implements Serializable {
    private String errCode;
    private String errMsg;

}
