package com.tsis.theater.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "예매를 위한 티켓 도메인 객체")
public class Ticket {
    @ApiModelProperty(value = "예매 좌석")
    private String seat;

    @ApiModelProperty(value = "예매 번호")
    private int ticketNo;

    @ApiModelProperty(value = "영화 번호")
    private int movieNo;

    @ApiModelProperty(value = "핸드폰 번호")
    private String phone;

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }

    public int getMovieNo() {
        return movieNo;
    }

    public void setMovieNo(int movieNo) {
        this.movieNo = movieNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                ", seat='" + seat + '\'' +
                ", ticketNo=" + ticketNo +
                ", movieNo=" + movieNo +
                ", phone='" + phone + '\'' +
                '}';
    }
}
