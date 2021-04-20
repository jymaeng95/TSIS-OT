package com.tsis.theater.controller;

import com.tsis.theater.domain.Movie;
import com.tsis.theater.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    //화면 1 보여주기
    @GetMapping("/info")
    public ResponseEntity<List<Movie>> getAllMovieInfo() {
        LOGGER.debug("모든 영화 정보 조회");
        List<Movie> test = movieService.getAllMoviesInfo();

        // mybatis -> camel 형식 변환 알아보기
        return new ResponseEntity<>(movieService.getAllMoviesInfo(), HttpStatus.OK);
    }
    @GetMapping("/{movieNo}")
    public ResponseEntity<Movie> getMovieInfo(@PathVariable("movieNo") int movieNo) {
        LOGGER.debug("영화 번호 별 영화 정보 조회");

        return new ResponseEntity<>(movieService.getMovieInfo(movieNo),HttpStatus.OK);
    }
}
