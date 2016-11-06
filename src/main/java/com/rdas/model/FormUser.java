package com.rdas.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by rdas on 06/11/2016.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormUser {
    private String id;

    @NotEmpty
    @Length(max = 140, min = 3)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String gender;
    private boolean agree;

    @NotNull
    private String country;
}
