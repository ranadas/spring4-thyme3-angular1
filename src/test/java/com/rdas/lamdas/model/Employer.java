package com.rdas.lamdas.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by rdas on 02/10/2016.
 */
@Getter
@Setter
@Builder
@ToString
public class Employer {
    //private String xId;

    @lombok.NonNull
    private String fullName;
    @lombok.NonNull
    private String email;
    @lombok.NonNull
    private String status;
}
