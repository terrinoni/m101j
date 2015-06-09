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
public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
        Spark.get("/", new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<>();
                    helloMap.put("name", "Freemarker");

                    helloTemplate.process(helloMap, writer);

                    System.out.println(writer);
                } catch(IOException | TemplateException ex) {
                    Spark.halt(500);
                    return null;
                }
                return writer;
            }
        });
    }
}
