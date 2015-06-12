/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.hw2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import it.terrinoni.hw2.utils.Helpers;
import static java.util.Arrays.asList;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Marco Terrinoni
 */
public class Homework {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("students");
        MongoCollection<Document> collection = database.getCollection("grades");
        
        Bson filter = eq("type", "homework");
        Bson sort = ascending(asList("student_id", "score"));
        
        MongoCursor<Document> cursor = collection
                .find(filter)
                .sort(sort)
                .iterator();
        
        double last_student_id = -1;
        
        try {
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                if(doc.getDouble("student_id") != last_student_id) {
                    last_student_id = doc.getDouble("student_id");
                    collection.deleteOne(doc);
                    System.out.println("Document for " + last_student_id + " with score " + String.valueOf(doc.getDouble("score")) + "  eliminated");
                }
                Helpers.printJson(doc);
            }
        } finally {
            cursor.close();
        }

        
    }
}
