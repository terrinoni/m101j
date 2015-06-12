/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Marco Terrinoni
 */
@Entity(value = "users", noClassnameStored = true)
@Indexes({
    @Index(value = "userName, -followers", name = "popular"),
    @Index(value = "lastActive", name = "idle", expireAfterSeconds = 1000000000) // the document is deleted after the specified number of seconds
})
public class GithubUser {

    @Id
    public String userName;
    public String fullName;
    @Property("since")
    public Date memberSince;
    public Date lastActive;
    @Reference(lazy = true) // do not load all the repositories when the github user is loaded (it's fetched only when requested)
    public List<Repository> repositories = new ArrayList<>(); // no validation
    public int followers = 0;
    public int following = 0;

    public GithubUser() {
    }
    
    public GithubUser(final String userName) {
        this.userName = userName;
    }
}
