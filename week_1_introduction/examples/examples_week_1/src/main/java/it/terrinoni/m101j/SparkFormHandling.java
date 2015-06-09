/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 *
 * @author Marco Terrinoni
 */
public class SparkFormHandling {
    public static void main(String[] args) {
        // Configure Freemarker
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");
        
        // Configure routes
        Spark.get("/", new Route() {

            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                try {
                    Map<String, Object> fruitsMap = new HashMap<>();
                    fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));
                    Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
                    StringWriter writer = new StringWriter(); // it will contains HTML code
                    fruitPickerTemplate.process(fruitsMap, writer);
                    return writer;
                } catch(IOException | TemplateException e) {
                    Spark.halt(500);
                    return null;
                }
            }
        });
        
        Spark.post("/favorite_fruit", new Route() {

            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                final String fruit = rqst.queryParams("fruit");
                if(fruit == null) {
                    return "Why don't you pick one?!";
                } else {
                    return "Your favorite fruit is " + fruit;
                }
            }
        });
    }
}
