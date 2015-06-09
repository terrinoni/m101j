/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j.spark;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;


/**
 *
 * @author Marco Terrinoni
 */
public class HelloWorldMongoDBSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/freemarker");
        
        MongoClient client = new MongoClient();
        
        MongoDatabase database = client.getDatabase("course");
        final MongoCollection<Document> collection = database.getCollection("hello");
        
        collection.drop();
        
        collection.insertOne(new Document("name", "MongoDB"));
        
        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    
                    Document document = collection.find().first();

                    helloTemplate.process(document, writer);
                    
                } catch(IOException | TemplateException ex) {
                    Spark.halt(500);
                    ex.printStackTrace();
                }
                return writer;
            }
        });
    }
}
