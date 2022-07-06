package com.example.trivia.model;

import java.io.Serializable;
import java.util.Map;

public class UserDetails implements Serializable {
    public int animals;
    public int food;
    public int movies;
    public int music;
    public int sport;
    public int score;
    public String userName;


    public UserDetails() {

    }

    public UserDetails(int animals, int food, int movies, int music , int sport,int score, String userName)
    {
        this.animals = animals;
        this.food = food;
        this.movies = movies;
        this.music = music;
        this.sport = sport;
        this.score =score;
        this.userName = userName;
    }

}
