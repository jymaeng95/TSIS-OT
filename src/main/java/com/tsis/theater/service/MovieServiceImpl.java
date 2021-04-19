package com.tsis.theater.service;

import com.tsis.theater.domain.Movie;
import com.tsis.theater.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMoviesInfo() {
        return movieRepository.getAllMovies();
    }
}
