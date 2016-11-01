package com.rdas.profiled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by rdas on 04/10/2016.
 */
@Profile("qa")
@Configuration
public class QAConfiguration {
    @PostConstruct
    public void init() {
        System.out.println(" \n\n ---- > In QA Construct \n\n");
    }

//    @Value("classpath:db-schema.sql")
//    private Resource schemaScript;
//
//    @Value("classpath:db-test-data.sql")
//    private Resource dataScript;

    @Bean
    public DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        builder.setName("QA-Database");
        builder.setScriptEncoding("UTF-8");
        builder.addScript("classpath:db-h2.sql");
        return builder.build();
            /*
        DataSource dataSource = createDataSource();
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
        */
    }

}