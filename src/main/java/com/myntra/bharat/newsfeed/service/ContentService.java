package com.myntra.bharat.newsfeed.service;

import com.myntra.bharat.newsfeed.model.entity.Content;
import com.myntra.bharat.newsfeed.model.entity.User;

import java.util.List;

/*
 * Developer : Bharat Verma
 * Created : Tue 17-Nov-2020 04:17 PM
 **/
public class ContentService {

    public void addPost(String post, User user, List<Content> posts) {
        Content content = new Content(posts.size()+1 , post,  user.getName() , true);
        posts.add(content);
    }

    /**
     * 1 Post (1) - R1 , R2 , R3  (RR1 RR2... )
     * 2 Post (2) -
     * 3 Post (3) -
     * @param comment
     * @param contentId
     * @param user
     * @param posts
     */
    public void addReply(String comment, int contentId, User user, List<Content> posts) {

        Content post = null;
        for ( Content content : posts)
            if(content.getId() == contentId)
                post = content;

        if(post != null) {
            posts.remove(post);
            post.addComment(comment, user.getName());
            posts.add(post);
        }
    }

    public void upVote (int contentId) {

    }
    public void downVote (int contentId) {

    }
}
