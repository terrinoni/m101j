/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import it.terrinoni.m101j.util.Helpers;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author Marco Terrinoni
 */
public class DeleteTest {public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("deleteTest");

        collection.drop();

        // insert 8 documents, with _id set to the value of the loop variable
        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document().append("_id", i));
        }
        
//        collection.deleteMany(gt("_id", 4));
        
        collection.deleteOne(eq("_id", 4));

        for (Document cur : collection.find().into(new ArrayList<Document>())) {
            Helpers.printJson(cur);
        }
    }
}
