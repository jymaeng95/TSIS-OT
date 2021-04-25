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
    public boolean reserveTicketing(List<Ticket> tickets, int ticketNo) {
        for(Ticket ticket : tickets) {
            ticket.setTicketNo(ticketNo);
            boolean rst = ticketRepository.insertTicketing(ticket) > 0;
            if(!rst)
                return false;
        }
        return true;
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
    public List<String> getReserveSeat(int ticketNo) {
        return ticketRepository.readSeatStatus(ticketNo);
    }

    @Override
    public List<String> getReserveSeatByMovieNo(int movieNo) {
        return ticketRepository.readSeatStatusByMovieNo(movieNo);
    }

    @Override
    public int getTicketingSeatCount(int movieNo) {
        return ticketRepository.readTicketingCountSeat(movieNo);
    }

    @Override
    public int getTicketNoRecent() {
        return ticketRepository.readTicketNoRecent();
    }

}
