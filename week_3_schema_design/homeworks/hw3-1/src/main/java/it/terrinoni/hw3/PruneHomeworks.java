/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.hw3;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Marco Terrinoni
 */
public class PruneHomeworks {

    public static void main(String[] args) {
        // MongoDB connection
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("school");
        MongoCollection<Document> collection = database.getCollection("students");

        // Get the cursor to the collection
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) { // iteare over all the students
                double minScore = Double.MAX_VALUE; // set the maximum value
                Document minDoc = null; // temporary minimum
                Document student = cursor.next(); // current score

                // Retrieve the scores array
                List<Document> scores = student.get("scores", ArrayList.class);
                for (Document score : scores) { // iterate over the scores
                    if (score.get("type", String.class).equals("homework")) { // get only the homeworks
                        System.out.println("Student " + student.getDouble("_id")
                                + " has homework score equals to " + score.getDouble("score"));
                        // Update the minimum score 
                        if (score.getDouble("score") < minScore) {
                            minScore = score.getDouble("score");
                            minDoc = score;
                        }
                    }
                }
                // Remove the minimum score
                scores.remove(minDoc);

                // Update the student document
                Bson filter = eq("_id", student.getDouble("_id"));
                Document update = new Document("$set", new Document("scores", scores));
                collection.updateOne(filter, update);
            }
        } finally {
            cursor.close(); // close the cursos
        }
    }
}
