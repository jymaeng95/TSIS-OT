package com.tsis.theater.domain;

public class Ticket {
    private int ticketId;
    private String seat;
    private int ticketNo;
    private int movieNo;
    private String phone;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

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
                "ticketId=" + ticketId +
                ", seat='" + seat + '\'' +
                ", ticketNo=" + ticketNo +
                ", movieNo=" + movieNo +
                ", phone='" + phone + '\'' +
                '}';
    }
}
