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
public class PersonDAOImpl implements PersonDAO{
    
    private List<Person> personList;

    public PersonDAOImpl(List<Person> personList) {
        this.personList = personList;
    }
    
    @Override
    public int insert(Person p) {
        personList.add(p);
        return 1;
    }

    @Override
    public int delete(int id) {
        Person p = getById(id);
        if(p != null){
            personList.remove(p);
            return 1;
        }else return 0;
    }

    @Override
    public Person getById(int id) {
        for(Person p : personList){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Person> getAll() {
        return personList;
    }

    @Override
    public Person getBySerialNo(int serialNo) {
        for(Person p : personList){
            if(p.getSerialNo() == serialNo){
                return p;
            }
        }
        return null;
    }
    
}
