package com.rdas.testci;

import com.rdas.profiled.QAConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by rdas on 02/10/2016.
 */
@Import({QAConfiguration.class})
@Configuration
public class TestingConfiguration {
}
