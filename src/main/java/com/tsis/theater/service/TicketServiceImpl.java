package com.tsis.theater.service;

import com.tsis.theater.domain.Ticket;
import com.tsis.theater.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public boolean reserveTicketing(Ticket ticket) {
        return ticketRepository.insertTicketing(ticket) > 0;
    }

    @Override
    public List<Map<String,Object>> getTicketingInfo(String phone) {
        return ticketRepository.readTicketing(phone);
    }

    @Override
    public boolean cancelTicketing(int ticketNo) {
        return ticketRepository.deleteTicketing(ticketNo) > 0;
    }

    @Override
    public List<String> getReserveSeat(int movieNo) {
        return ticketRepository.readSeatStatus(movieNo);
    }

    @Override
    public List<Integer> getTicketingSeatCount(List<Integer> movieNoList) {
        return ticketRepository.readTicketingCountSeat(movieNoList);
    }

}
