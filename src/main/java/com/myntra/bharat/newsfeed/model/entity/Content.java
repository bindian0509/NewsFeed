package com.myntra.bharat.newsfeed.model.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.myntra.bharat.newsfeed.model.constant.SocialNetwork.prettyTime;

/*
 * Developer : Bharat Verma
 * Created : Tue 17-Nov-2020 03:22 PM
 **/
@Data
public class Content {

    private int id;
    private String value;
    private String author;
    private Date time;
    private int upVote;
    private int downVote;
    private boolean post;
    private List<Content> comments;

    public Content(int id, String value, String author, boolean post) {
        this.id = id;
        this.value = value;
        this.author = author;
        this.time = new Date();
        this.upVote = 0;
        this.downVote = 0;
        this.post = post;
        this.comments = new ArrayList<>();
    }

    public Content() {
    }

    public void addComment (String value, String user) {
        Content content = new Content(this.comments.size()+1, value, user, false);
        this.comments.add(content);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id: "+id+"\n")
                .append(author+" ("+upVote+" upvote, "+downVote+" downvote)\n")
                .append(value+"\n")
                .append(prettyTime(time)+"\n")
                .append("\t"+(comments.size() > 0 ? comments : "")+"\n")
                .toString();
    }
}
