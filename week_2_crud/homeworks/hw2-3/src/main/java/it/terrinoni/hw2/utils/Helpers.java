/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.hw2.utils;

import java.io.StringWriter;
import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

/**
 *
 * @author Marco Terrinoni
 */
public class Helpers {
    public static void printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(), new JsonWriterSettings(JsonMode.SHELL, false));
        new DocumentCodec().encode(jsonWriter, document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }
}
