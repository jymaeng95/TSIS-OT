package com.tsis.theater.controller;

import com.tsis.theater.domain.Ticket;
import com.tsis.theater.service.MovieService;
import com.tsis.theater.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ticketing")
@Api(tags = "Ticket", value = "Ticket controller")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    MovieService movieService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @ApiOperation(value="영화 예매하기")
    @PostMapping("/reserve")
    public ResponseEntity<String> reserveTicketing(@RequestBody Ticket ticket) {
        LOGGER.debug("예매 정보 입력 시도");

        if(ticketService.reserveTicketing(ticket))
            return new ResponseEntity<>("예매 완료",HttpStatus.OK);
        return new ResponseEntity<>("예매 실패", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 1.예매정보 확인 , 2. 예매정보 기반 영화번호 정보 가져오기(MovieController), 3.Ticket에서 좌석 가져오기
    @ApiOperation(value="예매 정보 확인")
        @GetMapping("/{phone}")
        public ResponseEntity<?> getTicketingInfo(@PathVariable("phone") String phone) {
            LOGGER.debug("예매 정보 확인 시도");

            return new ResponseEntity<>(ticketService.getTicketingInfo(phone),HttpStatus.OK);
    }

    @ApiOperation(value="예매 취소 시도")
    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelTicketing(int ticketNo) {
        LOGGER.debug("예매 취소 시도");

        if(ticketService.cancelTicketing(ticketNo))
            return new ResponseEntity<>("예매 취소 완료", HttpStatus.OK);
        return new ResponseEntity<>("예매 취소 실패", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value="영화에 따른 예매 좌석 상태 확인")
    @GetMapping("/seatStatus/{movieNo}")
    public ResponseEntity<List<String>> getSeatStatus(@PathVariable("movieNo") int movieNo){
        LOGGER.debug("영화에 따른 예매 좌석 상태 확인");

        return new ResponseEntity<>(ticketService.getReserveSeat(movieNo), HttpStatus.OK);
    }

    @ApiOperation(value="현재 예매된 좌석 개수 가져오기")
    @GetMapping("/seatStatus/current")
    public ResponseEntity<List<Integer>> getCurrentScreenStatus() {
        LOGGER.debug("현재 예매된 좌석 개수 가져오기");
        List<Integer> curSeatList = new ArrayList<>();

        for(int i=1;i<= movieService.getCurrentScreenCount();i++){
            int ticketingSeatCount = ticketService.getTicketingSeatCount(i);
            curSeatList.add(ticketingSeatCount);
        }

        return new ResponseEntity<>(curSeatList,HttpStatus.OK);
    }
}
