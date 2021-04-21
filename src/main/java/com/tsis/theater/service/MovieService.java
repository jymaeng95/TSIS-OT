package com.tsis.theater.service;

import com.tsis.theater.domain.Movie;

import java.util.List;

public interface MovieService {
    //모든 영화 정보 가져오기
    List<Movie> getAllMoviesInfo();

    //영화 정보 가져오기
    Movie getMovieInfo(int movieNo);

    //현재상영중인 영화 개수 가져오기
    int getCurrentScreenCount();

    //상영관 정보를 통해 간략한 영화 정보 가져오기
    Movie getMovieInfoFromRoom(int room);
}
