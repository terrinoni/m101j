/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Marco Terrinoni
 */
public class Controller {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("photo-sharing");

        MongoCollection<Document> albums = database.getCollection("albums");
        MongoCollection<Document> images = database.getCollection("images");

        albums.createIndex(new Document("images", 1));

        // Get the iterator of the whole collection
        MongoCursor<Document> cursor = images.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document currImg = cursor.next();
                Document foundImg = albums.find(eq("images", currImg.getDouble("_id"))).first();
                if (foundImg == null) {
                    //System.out.println(currImg.getDouble("_id") + " deleted.");
                    images.deleteOne(currImg);
                }
                //System.out.println(currImg.getDouble("_id") + " is ok.");
            }
        } finally {
            cursor.close();
        }
        
        long numImgs = images.count(eq("tags", "sunrises"));
        System.out.println("The total number of images with the tag \"sunrises\" after the removal of orphans is: " + String.valueOf(numImgs));
    }
}
