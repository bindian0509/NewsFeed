package com.myntra.bharat.newsfeed.model.constant;

import lombok.SneakyThrows;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/*
 * Developer : Bharat Verma
 * Created : Tue 17-Nov-2020 04:05 PM
 **/
public class SocialNetwork {

    public static final String LOGIN = "login";
    public static final String SIGN_UP = "signup";
    public static final String POST = "post";
    public static final String REPLY = "reply";
    public static final String FOLLOW = "follow";
    public static final String DOWNVOTE = "downvote";
    public static final String UPVOTE = "upvote";


    public static String getTimeFromLocalDateTime(LocalDateTime time ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  time.format(formatter);
    }

    public static String prettyTime(Date time) {
        PrettyTime p = new PrettyTime();
        return p.format(minusOneDayCalendar(time));
    }
    @SneakyThrows
    public static Date minusOneDayCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }
}
