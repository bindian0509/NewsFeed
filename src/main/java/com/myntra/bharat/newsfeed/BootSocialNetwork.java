package com.myntra.bharat.newsfeed;

import com.myntra.bharat.newsfeed.model.constant.SocialNetwork;
import com.myntra.bharat.newsfeed.model.entity.Content;
import com.myntra.bharat.newsfeed.model.entity.User;
import com.myntra.bharat.newsfeed.service.ContentService;
import com.myntra.bharat.newsfeed.service.UserService;
import com.myntra.bharat.newsfeed.utils.FileResourcesUtils;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/*
 * Developer : Bharat Verma
 * Created : Tue 17-Nov-2020 03:20 PM
 **/
public class BootSocialNetwork {

    FileResourcesUtils fru = new FileResourcesUtils();

    private List<User> users;
    private List<Content> posts;

    private User sessionUser = null;

    private UserService userService;
    private ContentService contentService;

    public BootSocialNetwork() {
        this.users = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.userService = new UserService();
        this.contentService = new ContentService();
    }

    @SneakyThrows
    public static void main(String[] args) {

        BootSocialNetwork socialNetwork = new BootSocialNetwork();
        List<String> commands = socialNetwork.fru.getFileAsList("script");

        //Driver Loop
        for (String command : commands) {

            String [] params = command.split("~");
            System.out.println("# COMMAND : "+command);
           /* System.out.println("---------------------------------------------------------");
            System.out.println(socialNetwork.users);
            System.out.println(socialNetwork.posts);
            System.out.println("---------------------------------------------------------");*/
            String action = params[0];

            if(action != null && action.length()>0) {
                //System.out.println(socialNetwork.users);
                if(action.equals(SocialNetwork.SIGN_UP)) {
                    String user = params[1];
                    if(user != null && user.length()>0) {
                        if(!socialNetwork.userService.signupUser(user, socialNetwork.users)) {
                            System.out.println("User Already exists");
                        }
                    }
                    continue;
                }
                if(action.equals(SocialNetwork.LOGIN)) {
                    String user = params[1];
                    if(user != null && user.length()>0) {
                        User loginUser = socialNetwork.userService.loginUser(user, socialNetwork.users);
                        if(loginUser == null) {
                            System.out.println("User doesn't exists");
                        } else {
                            socialNetwork.sessionUser = loginUser;
                            // news feed
                            /*List<Content> userPosts = socialNetwork.posts.stream().filter(content ->
                                    content.getAuthor().equals(socialNetwork.sessionUser)).collect(Collectors.toList());*/
                            System.out.println(socialNetwork.posts);
                        }
                    }
                    continue;
                }
                if(action.equals(SocialNetwork.POST)) {
                    if(socialNetwork.sessionUser == null) {
                        System.out.println("Login required to post");
                        continue;
                    }
                    String postContent = params[1];
                    socialNetwork.contentService.addPost(postContent, socialNetwork.sessionUser, socialNetwork.posts);
                    continue;
                    //System.out.println(socialNetwork.posts);
                }
                if(action.equals(SocialNetwork.REPLY)) {
                    if(socialNetwork.sessionUser == null) {
                        System.out.println("Login required to reply");
                        continue;
                    }
                    int contentId = Integer.parseInt(params[1]);
                    String reply = params[2];
                    socialNetwork.contentService.addReply(reply, contentId , socialNetwork.sessionUser, socialNetwork.posts);
                    continue;

                }
                if(action.equals(SocialNetwork.FOLLOW)) {
                    if(socialNetwork.sessionUser == null) {
                        System.out.println("Login required to follow");
                        continue;
                    }
                    String userName = params[1];
                    socialNetwork.userService.follow(userName, socialNetwork.sessionUser, socialNetwork.users);
                    continue;
                }
                if(action.equals(SocialNetwork.UPVOTE)) {
                    int contentId = Integer.parseInt(params[1]);
                    socialNetwork.contentService.upVote(contentId);
                }
                if(action.equals(SocialNetwork.DOWNVOTE)) {
                    int contentId = Integer.parseInt(params[1]);
                    socialNetwork.contentService.downVote(contentId);
                }
            }

        }

    }
}
