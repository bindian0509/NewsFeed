package com.myntra.bharat.newsfeed.service;

import com.myntra.bharat.newsfeed.model.entity.User;

import java.util.List;

/*
 * Developer : Bharat Verma
 * Created : Tue 17-Nov-2020 03:39 PM
 **/
public class UserService {


    public boolean signupUser(String userName, List<User> usersDatabase) {

        // TODO - validations  if part of the requirement
        for (User user : usersDatabase)
            if(user.getName().equals(userName))
                return false;

        User user = new User(userName);
        usersDatabase.add(user);
        return true;

    }

    public User loginUser(String userName, List<User> usersDatabase)  {
        User user = null;
        for (User loopUser : usersDatabase)
            if(loopUser.getName().equals(userName))
                user = loopUser;

        return user;
    }

    public void follow(String userName, User loggedInUser, List<User> users) {

        User toFollow = null;
        for (User usr : users)
            if(usr.getName().equals(userName))
                toFollow = usr;
        if(toFollow != null) {
            loggedInUser.getFollowing().add(toFollow.getName());
        }
    }

}
