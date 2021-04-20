package com.tsis.theater.repository;

import com.tsis.theater.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {
    //모든 영화 정보 불러오기
    List<Movie> getAllMovies();

    //영화 정보 불러오기
    Movie getMovie(int movieNo);
}
