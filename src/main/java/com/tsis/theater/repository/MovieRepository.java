package com.tsis.theater.repository;

import com.tsis.theater.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {
    List<Movie> getAllMovies();
}
