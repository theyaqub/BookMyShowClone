
package com.alpha.theaterService.dto;

public class MovieResponseDTO {

    private Long movieId;
    private String title;
    private String description;
    private String cast;
    private String language;

    // Default Constructor
    public MovieResponseDTO() {
    }

    // Parameterized Constructor
    public MovieResponseDTO(Long movieId, String title,
                            String description, String cast,
                            String language) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.cast = cast;
        this.language = language;
    }

    // Getters and Setters
    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
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

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "MovieResponseDTO{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cast='" + cast + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
