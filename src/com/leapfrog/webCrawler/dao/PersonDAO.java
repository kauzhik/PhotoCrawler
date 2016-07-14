/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.webCrawler.dao;

import com.leapfrog.webCrawler.entity.Person;
import java.util.List;

/**
 *
 * @author kaushik
 */
public interface PersonDAO {
    
    int insert(Person p);
    int delete(int id);
    Person getById(int id);
    Person getBySerialNo(int serialNo);
    List<Person> getAll();
}
