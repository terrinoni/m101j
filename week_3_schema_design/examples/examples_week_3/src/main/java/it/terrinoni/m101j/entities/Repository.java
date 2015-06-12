/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.m101j.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Marco Terrinoni
 */
@Entity("repos")
class Repository {

    @Id
    public String name;
    @Reference
    public Organization organization;
    @Reference
    public GithubUser owner;
    public Settings settings = new Settings();

    public Repository() {
    }

    public Repository(final Organization organization, final String name) {
        this.organization = organization;
        this.name = organization.name + "/" + name;
    }
    
    public Repository(final GithubUser owner, final String name) {
        this.owner = owner;
        this.name = name;
    }
}
