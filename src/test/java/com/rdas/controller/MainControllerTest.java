package com.rdas.controller;

import com.rdas.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by rdas on 15/10/2016.
 */
@ActiveProfiles({"qa"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
//@ActiveProfiles("test")
public class MainControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldReturnErrorView() throws Exception {
        mockMvc.perform(get("/anything"))
                .andDo(print())
                .andExpect(view().name("hello"))
                .andReturn()
                //.andExpect(content().contentType("text/html;charset=ISO-8859-1"))
//                .andExpect(content().string(containsString("anything")))
        ;

        /**
         * http://stackoverflow.com/questions/28944538/empty-content-in-spring-mvc-test
         * You cannot write assertions for the content of a JSP page because JSP pages are rendered by a servlet container and Spring MVC Test doesn't run a servlet container. You can only verify that the view name is correct and/or the request is forwarded to the correct url.

         However, you can write assertions for the content of your views if you use a view technology that doesn't require a servlet container (such as Velocity or Thymeleaf).

         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/jobsdetails")
                .accept(MediaType.TEXT_HTML))
                .andReturn();
        String content = result.getResponse().getContentAsString();

        ResultActions result2 =
                this.mockMvc.perform(get("/jobsdetails"))
                        //.sessionAttr(Constants.SESSION_USER, user)
                        //.param("parameter", "parameterValue"))
                        .andExpect(status().isOk());

        content = result2.andReturn().getResponse().getContentAsString();

        System.out.println(content);

    }


}