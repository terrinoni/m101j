/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import it.terrinoni.m101j.util.Helpers;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Marco Terrinoni
 */
public class FindTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findTest");
        
        collection.drop();
        
        // insert 10 documents
        for(int i = 0; i < 10; i++) {
            collection.insertOne(new Document("x", i));
        }
        
        System.out.println("Find one: ");
        Document first = collection.find().first();
        Helpers.printJson(first);
        
        System.out.println("Find all with into: ");
        List<Document> all = collection.find().into(new ArrayList<Document>());
        for(Document doc : all) {
            Helpers.printJson(doc);
        }
        
        System.out.println("Find all with iteration: ");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                Helpers.printJson(doc);
            }
        } finally {
            cursor.close();
        }
        
        System.out.println("Count: ");
        long count = collection.count();
        System.out.println(count);
    }
}
