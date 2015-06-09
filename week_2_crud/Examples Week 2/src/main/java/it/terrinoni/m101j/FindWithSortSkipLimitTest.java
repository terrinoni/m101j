/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import it.terrinoni.m101j.util.Helpers;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Marco Terrinoni
 */
public class FindWithSortSkipLimitTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithSortTest");

        collection.drop();

        // insert 10 documents
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                collection.insertOne(new Document()
                        .append("i", i)
                        .append("j", j));
            }
        }

        Bson projection = fields(include("i", "j"), excludeId());
//        Bson sort = new Document("i", 1).append("j", -1); // i asc, j desc
//        Bson sort = ascending("i");
//        Bson sort = descending("j");
        Bson sort = orderBy(ascending("i"), descending("j"));

        List<Document> all = collection
                .find()
                .sort(sort)
                .skip(20)
                .limit(50)
                .projection(projection)
                .into(new ArrayList<Document>());

        for (Document doc : all) {
            Helpers.printJson(doc);
        }

    }
}
