package com.tsis.theater.service;

import com.tsis.theater.domain.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketService {
    //예매 하기
    boolean reserveTicketing(Ticket ticket);

    //예매 정보 가져오기
    List<Map<String,Object>> getTicketingInfo(String phone);

    //예매 취소
    boolean cancelTicketing(int ticketNo);

    //예매 번호로 예매된 좌석 불러오기
    List<String> getReserveSeat(int ticketNo);

    //영화 번호로 예매된 좌석 불러오기
    List<String> getReserveSeatByMovieNo(int movieNo);

    //현재 예매된 좌석 개수 가져오기
    int getTicketingSeatCount(int movieNo);
}
