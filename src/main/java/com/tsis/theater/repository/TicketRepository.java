package com.tsis.theater.repository;

import com.tsis.theater.domain.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TicketRepository {
    //CRUD
    //예매하기 (insert)
    int insertTicketing(Ticket ticket);

    //예매확인 (select) -> group by
    List<Map<String,Object>> readTicketing(String phone);

    //예매취소 (delete)
    int deleteTicketing(int ticketNo);

    //예매변경 (update)
//    int updateTicketing(Ticket ticket);

    //현재 이용 중인 좌석 개수 가져오기
    //Integer list로 가져오기
    int readTicketingCountSeat(int movieNo);

    //예매 번호에 따른 좌석 정보 가져오기
    List<String> readSeatStatus(int ticketNo);

    List<String> readSeatStatusByMovieNo(int movieNo);
}
