
package com.alpha.theaterService.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    //date,time,screeno,theater
    private String title;
    private String description;
    private double duration;
    private String language;
    private int screenNo;

    // No-argument constructor
    public Show() {
    }

    // Parameterized constructor
    public Show(Long id, Movie movie, Theater theater,
            String title, String description, double duration,
            String language, int screenNo) {

        this.id = id;
        this.movie = movie;
        this.theater = theater;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.language = language;
        this.screenNo = screenNo;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getScreenNo() {
        return screenNo;
    }

    public void setScreenNo(int screenNo) {
        this.screenNo = screenNo;
    }
}
