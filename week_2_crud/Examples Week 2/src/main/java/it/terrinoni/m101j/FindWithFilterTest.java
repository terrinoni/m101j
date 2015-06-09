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
import java.util.List;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Marco Terrinoni
 */
public class FindWithFilterTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        // insert 10 documents
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100)));
        }

//        Bson filter = new Document("x", 0)
//                .append("y", new Document("$gt", 10)
//                        .append("$lt", 90));
        
        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

        List<Document> all = collection.find(filter).into(new ArrayList<Document>());

        for (Document doc : all) {
            Helpers.printJson(doc);
        }

        long count = collection.count(filter);
        System.out.println();
        System.out.println(count);

    }
}
