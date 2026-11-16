
package com.alpha.theaterService.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowRequestDto {

    private long movieId;

    private long theaterId;

    private LocalDate showDate;

    private LocalTime showTime;

    private int screenNumber;

    // Getters and Setters

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(long theaterId) {
        this.theaterId = theaterId;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }
}
