package com.tsis.theater.domain;

public class Movie {
    private int movieNo;
    private int room;
    private String title;
    private int price;
    private String startTime;

    public int getMovieNo() {
        return movieNo;
    }

    public void setMovieNo(int movieNo) {
        this.movieNo = movieNo;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieNo=" + movieNo +
                ", room=" + room +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
