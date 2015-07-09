/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Marco Terrinoni
 */
public class Question {

    public static void main(String[] args) {
        MongoClient c = new MongoClient();
        MongoDatabase db = c.getDatabase("test");
        MongoCollection<Document> animals = db.getCollection("animals");

        Document animal = new Document("animal", "monkey");

        animals.insertOne(animal);
        
        animal.remove("animal");
        animal.append("animal", "cat");
        try {
            animals.insertOne(animal);
        } catch (MongoWriteException ex) {
            System.err.println(animal.getString("animal") + " not inserted");
        }

        animal.remove("animal");
        animal.append("animal", "lion");
        try {
            animals.insertOne(animal);
        } catch (MongoWriteException ex) {
            System.err.println(animal.getString("animal") + " not inserted");
        }
        
        long tot = animals.count();
        
        System.out.println("Total number of inserted animals: " + tot);
    }
}
