/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.webCrawler;

import com.leapfrog.webCrawler.controller.PersonController;
import com.leapfrog.webCrawler.dao.PersonDAOImpl;
import com.leapfrog.webCrawler.entity.Person;
import com.leapfrog.webCrawler.util.Grabber;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kaushik
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        PersonController personController = new PersonController(new PersonDAOImpl(new ArrayList<>()));
        String link = "http://cybersansar.com/result_search.php";
    //       Get page
        String content = Grabber.get(link);
    //       Get dropdownlist
        Matcher matcher = personController.getMatch("<select name=\"mname\" onchange=\"this\\.form\\.submit\\(\\)\\;\">(.*?)</select>", content);
        
        if(matcher.find()){
            String dropDownContent = matcher.group(1);
    //        Get person list
            Matcher fMatcher = personController.getMatch("<option value=(.*?)>                              (.*?)</option>", dropDownContent);

    //        Store person list using DAO
            personController.storeList(fMatcher);
            
    //        Get person list ans display
            personController.viewList();
           
    //        Get person acc. to input
            Scanner input = new Scanner(System.in);
            Person p = personController.decidePerson(input);
            
//            Crawl for person and get image
            String params = "mname="+p.getId();
            String personName = p.getName().trim();
            String personContent = Grabber.post(link, params);
            Matcher gMatcher = personController.getMatch("<a href=\"thumbnail_view\\.php\\?gal_id=(.*?)\" class=\"photolink\"><img src=\"(.*?)\" width=\"100\" height=\"150\" border=\"0\" /><br />                                          <span class=\"galleryname\">(.*?)</span></a>", personContent);
//            Download person's images
            int counter =1;
            while(gMatcher.find()){
                StringBuilder builder = new StringBuilder();
                builder.append("http://cybersansar.com/");
                builder.append(gMatcher.group(2));
                if(Grabber.getImage(builder.toString(), personName, counter) == 1){
                    System.out.println("Successfully downloaded images for "+personName);
                }
                counter++;
            }
            
        }
    }
    
}
