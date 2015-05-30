/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 *
 * @author Marco Terrinoni
 */
public class SparkRoutes {

    public static void main(String[] args) {
        Spark.get("/", new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                return "Hello World\n";
            }
        });
        Spark.get("/test", new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                return "This is a test page\n";
            }
        });
        Spark.get("/echo/:thing", new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                return rqst.params(":thing");
            }
        });
    }
}
