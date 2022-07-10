package com.tsis.theater.repository;

import com.tsis.theater.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {
    //모든 영화 정보 가져오기
    List<Movie> getAllRoomMovies(int room);

    //영화 정보 불러오기
    Movie getMovie(int movieNo);

    //현재 상영중인 영화 개수 가져오기
    int getCurrentScreenCount();

    //상영관 별 영화 정보 가져오기
    Movie getMovieFromRoom(int room);
}
