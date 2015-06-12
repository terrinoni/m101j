/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j.entities;

import java.util.Date;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Version;
import org.mongodb.morphia.utils.IndexDirection;

/**
 *
 * @author Marco Terrinoni
 */
@Entity("orgs")
public class Organization {

    @Id
    public String name;
    @Indexed(value = IndexDirection.ASC, name = "", unique = false, dropDups = false,
            background = false, sparse = false, expireAfterSeconds = -1)
    public Date created;
    @Version("v")
    public long version;

    public Organization() {
    }

    public Organization(final String name) {
        this.name = name;
    }
}
