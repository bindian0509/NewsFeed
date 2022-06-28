package com.myntra.bharat.newsfeed.model.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/*
 * Developer : Bharat Verma
 * Created : Tue 17-Nov-2020 03:22 PM
 **/
@Data
public class User {

    private String name;
    // enforced schema for usernames as list
    private Set<String> followers;
    private Set<String> following;

    public User(String name) {
        this.name = name;
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                '}';
    }
}
