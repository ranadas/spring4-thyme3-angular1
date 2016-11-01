package com.rdas.lamdas.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by rdas on 02/10/2016.
 */
public class MapperTransformTest {
    Employer employer1 = null;
    Employer employer2 = null;
    Employer employer3 = null;
    Employer employer4 = null;

    @Before
    public void inint() {
        employer1 = Employer.builder().fullName("Rana Das").email("rdas@rmail.com").status("SAVE").build();
        employer2 = Employer.builder().fullName("Jeinnifer Simonetti").email("js@rmail.com").status("SUBMIT").build();
        employer3 = Employer.builder().fullName("Connal Murphy").email("cm@rmail.com").status("APROOVE").build();
        employer4 = Employer.builder().fullName("Niall MgGuire").email("nm@rmail.com").status("REJECT").build();
    }

    @Test
    public void transformSingleObject() {
        EmployerUI employerUI = employerToEmloyerUI.apply(employer1);
        assertEquals(employerUI.getFullName(), employer1.getFullName());
    }

    @Test
    public void convertCollection() {
        List<Employer> gLocations = new ArrayList<>();
        gLocations.add(employer1);
        gLocations.add(employer2);
        gLocations.add(employer3);
        gLocations.add(employer4);

        List<EmployerUI> myLocations = gLocations.stream()
                .map(employerToEmloyerUI)
                .collect(Collectors.<EmployerUI> toList());

        System.out.printf(myLocations.toString());

    }

    private Function<Employer, EmployerUI> employerToEmloyerUI = employer -> {
        EmployerUI.EmployerUIBuilder employerUIBuilder = EmployerUI.builder().fullName(employer.getFullName()).status(employer.getStatus());
        if (isAdmin("XX14") && (employer.getStatus().equalsIgnoreCase("REJECT") || employer.getStatus().equalsIgnoreCase("SUBMIT"))) {
            employerUIBuilder.showButton(true);
        }
        return employerUIBuilder.build();
    };

    private boolean isAdmin(String name) {
        return !StringUtils.isEmpty(name);
    }
}
