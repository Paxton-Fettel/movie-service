package com.example.movie_service.service;

import com.example.movie_service.entity.Movie;
import com.example.movie_service.entity.Review;
import com.example.movie_service.repository.MovieRepository;
import com.example.movie_service.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final MovieService movieService;

    public ReviewService(
            ReviewRepository reviewRepository,
            MovieRepository movieRepository,
            MovieService movieService
    ) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review addReview(String imdbId, String comment) {
        Movie movie = movieService.getMovieByImdbId(imdbId);
        if (movie == null) {
            return null;
        } else {
            Review review = new Review();
            review.setComment(comment);
            reviewRepository.save(review);
            movie.getReviewIds().add(review);
            movieRepository.save(movie);
            return review;
        }
    }
}
