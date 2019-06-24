package io.github.brunolellis.playlist.usecase.genre;

public enum Genre {
    PARTY("party"),
    POP("pop"),
    ROCK("rock"),
    CLASSICAL("classical");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return this.genre;
    }
}