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
import static com.mongodb.client.model.Projections.*;
import it.terrinoni.m101j.util.Helpers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Marco Terrinoni
 */
public class FindWithProjectionTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithProjectionTest");

        collection.drop();

        // insert 10 documents
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100))
                    .append("i", i));
        }

//        Bson filter = new Document("x", 0)
//                .append("y", new Document("$gt", 10)
//                        .append("$lt", 90));
        
        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

//        Bson projection = new Document("x", 0) // exclude x
//                .append("i", 0); // exclude i
        
//        Bson projection = new Document("y", 1) // include y
//                .append("i", 1) // include i
//                .append("_id", 0); // exclude _id
        
//        Bson projection = Projections.exclude("x", "_id"); // exclude x, _id
        
//        Bson projection = Projections.include("y", "i"); // include y, i
        
        Bson projection = fields(include("y", "i"), excludeId());

        List<Document> all = collection
                .find(filter)
                .projection(projection)
                .into(new ArrayList<Document>());

        for (Document doc : all) {
            Helpers.printJson(doc);
        }

    }

}
