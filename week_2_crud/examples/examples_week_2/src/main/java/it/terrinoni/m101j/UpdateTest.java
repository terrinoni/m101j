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
import com.mongodb.client.model.UpdateOptions;
import it.terrinoni.m101j.util.Helpers;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author Marco Terrinoni
 */
public class UpdateTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("findWithFilterTest");

        collection.drop();

        // insert 8 documents, with both _id and x set to the value of the loop variable
        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document().append("_id", i).append("x", i));
        }

//        collection.replaceOne(eq("x", 5), new Document("_id", 5)
//                .append("x", 20)
//                .append("update", true));
        
//        collection.updateOne(eq("x", 5), new Document("$set", new Document("x", 20)));
        
//        collection.updateMany(gte("_id", 5), new Document("$inc", new Document("x", 1)));
        
        collection.updateOne(eq("_id", 9),
                new Document("$set", new Document("x", 20)),
                new UpdateOptions().upsert(true));

        for (Document cur : collection.find().into(new ArrayList<Document>())) {
            Helpers.printJson(cur);
        }
    }
}
