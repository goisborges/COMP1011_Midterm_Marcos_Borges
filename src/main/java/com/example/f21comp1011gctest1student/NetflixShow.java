package com.example.f21comp1011gctest1student;

public class NetflixShow {
        private String showId;
        private String type;
        private String title;
        private String rating;
        private String director;
        private String cast;


    public NetflixShow(String showId, String type, String title, String rating, String director, String cast) {
        this.showId = showId;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.rating = rating;

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
