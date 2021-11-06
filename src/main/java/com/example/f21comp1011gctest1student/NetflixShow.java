package com.example.f21comp1011gctest1student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetflixShow {
        private String showId;
        private String type;
        private String title;
        private String rating;
        private String director;
        private String cast;


    public NetflixShow(String showId, String type, String title, String rating, String director, String cast) {

        //create a Regex to check the showId pattern
        Pattern p = Pattern.compile("[s][0-9]*");
        Matcher m = p.matcher(showId);

        //or
//        if (showId.matches("[s]\\d*"))
//            this.showId = showId;

        if (m.find() ){
            this.showId = showId;
        }
        else {
            throw new IllegalArgumentException("Invalid showId");
        }



        //Movie pr TV Show are the options
        if (type.equals("TV Show") || type.equals("Movie")){
            this.type = type;
        }
        else {
            throw new IllegalArgumentException("Invalid type");
        }

        //title should hold at least 2 chars
        if (title.length() >= 2){
            this.title = title;
        }
        else {
            throw new IllegalArgumentException("Invalid title");
        }

        //rating should be one of the following "PG-13","R","TV-14","TV-G","TV-MA","TV-Y","TV-Y7"
        List<String> ratingsList = Arrays.asList("PG-13","R","TV-14","TV-G","TV-MA","TV-Y","TV-Y7");
        if (ratingsList.contains(rating)){
            this.rating = rating;
        }
        else {
            throw new IllegalArgumentException("Invalid rating");
        }

        //director should hold at least 2 chars
        if (director.length() >= 2){
            this.director = director;
        }
        else {
            throw new IllegalArgumentException("Invalid director");
        }

        //cast should hold at least 5 chars
        if (cast.length() >= 5){
            this.cast = cast;
        }
        else {
            throw new IllegalArgumentException("Invalid cast");
        }

    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


}
