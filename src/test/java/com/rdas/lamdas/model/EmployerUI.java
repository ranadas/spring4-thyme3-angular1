package com.rdas.lamdas.model;

import lombok.Builder;
import lombok.ToString;

/**
 * Created by rdas on 02/10/2016.
 */
@lombok.Getter
@lombok.Setter
@Builder
@ToString
public class EmployerUI {
    private String fullName;
    private String status;
    private boolean showButton;
}
