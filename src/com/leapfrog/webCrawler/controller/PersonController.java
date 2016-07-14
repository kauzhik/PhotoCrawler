/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.webCrawler.controller;

import com.leapfrog.webCrawler.dao.PersonDAO;
import com.leapfrog.webCrawler.entity.Person;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kaushik
 */
public class PersonController {
    private PersonDAO personList;
    
    public PersonController(PersonDAO personList){
        this.personList = personList;
    }
//    To store list of person
    public void storeList(Matcher fMatcher){
            int count = 0;
            while(fMatcher.find()){
                if(count != 0){
                    Person person = new Person();
                    person.setName(fMatcher.group(2));
                    person.setId(Integer.parseInt(fMatcher.group(1)));
                    person.setSerialNo(count);
                    personList.insert(person);
                }
                count++;
            }
    }
//    To show list of person
    public void viewList(){
        System.out.println("Type the serial no. to select the person: ");
            int counter = 1;
            for(Person p : personList.getAll()){
                System.out.println(counter+ ". " + p.getName());
                counter++;
                
            }
    }
//    To decide person according to user input
    public Person decidePerson(Scanner input){
        int serialNo = Integer.parseInt(input.next());
        Person p = personList.getBySerialNo(serialNo);
        if(p != null){
            return p;
//            System.out.println("You selected: "+p.getName());
        }
        return null;
    }
    
    public Matcher getMatch(String regex, String content){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(content);
    }
    
}
