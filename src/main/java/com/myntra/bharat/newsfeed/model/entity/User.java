package com.myntra.bharat.newsfeed.model.entity;

import com.google.common.base.MoreObjects;
import lombok.Data;

import java.util.*;

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
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("followers", followers.size())
                .add("following", following.size())
                .toString();
    }
}
