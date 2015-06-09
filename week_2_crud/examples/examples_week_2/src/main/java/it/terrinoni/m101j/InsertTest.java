/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.terrinoni.m101j.util.Helpers;
import java.util.Arrays;
import org.bson.Document;

/**
 *
 * @author Marco Terrinoni
 */
public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTest");
        
        coll.drop();
        
        Document smith = new Document("name", "Smith")
                .append("age", 30)
                .append("profession", "programmer");
        Document jones = new Document("name", "Jones")
                .append("age", 25)
                .append("profession", "hacker");
        
        Helpers.printJson(smith);
        
        //coll.insertOne(smith);
        coll.insertMany(Arrays.asList(smith, jones));
        
        Helpers.printJson(smith);
        Helpers.printJson(jones);
    }
}
