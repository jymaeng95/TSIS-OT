package com.tsis.theater.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "영화 정보 저장 도메인 객체")
public class Movie {
    @ApiModelProperty(value = "영화 번호")
    private int movieNo;

    @ApiModelProperty(value = "상영관 번호")
    private int room;

    @ApiModelProperty(value = "영화 제목")
    private String title;

    @ApiModelProperty(value = "티켓 가격")
    private int price;

    @ApiModelProperty(value = "영화 시작 시간")
    private String startTime;

    @ApiModelProperty(value = "상영관 좌석수")
    private int totalSeat;

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatCol() {
        return seatCol;
    }

    public void setSeatCol(int seatCol) {
        this.seatCol = seatCol;
    }

    @ApiModelProperty(value = "상영관 좌석 행")
    private int seatRow;

    @ApiModelProperty(value = "상영관 좌석 열")
    private int seatCol;

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }


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
                ", totalSeat=" + totalSeat +
                ", seatRow=" + seatRow +
                ", seatCol=" + seatCol +
                '}';
    }
}

