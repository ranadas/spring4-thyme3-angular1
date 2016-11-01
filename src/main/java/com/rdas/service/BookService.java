package com.rdas.service;

import java.util.List;

/**
 * Created by rdas on 16/10/2016.
 */
public interface BookService {

    String findById(long id);

    String findByName(String name);

    List findAll();
}
