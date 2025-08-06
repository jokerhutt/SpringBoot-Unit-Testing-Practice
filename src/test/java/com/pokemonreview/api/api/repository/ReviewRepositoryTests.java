package com.pokemonreview.api.api.repository;

import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTests {

    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewRepositoryTests(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Test
    public void ReviewRepository_SaveAll_ReturnsSavedReview() {

        //Arrange
        Review review = Review.builder().title("title").content("content").stars(5).build();

        //Act
        Review savedReview = reviewRepository.save(review);

        //Assert
        Assertions.assertThat(savedReview).isNotNull();
        Assertions.assertThat(savedReview.getId()).isGreaterThan(0);

    }

    @Test
    public void ReviewRepository_GetAll_ReturnsMoreThanOneReview() {

        Review review = Review.builder().title("title").content("content").stars(5).build();
        Review review2 = Review.builder().title("titleTwo").content("contentTwo").stars(2).build();

        reviewRepository.save(review);
        reviewRepository.save(review2);

        List<Review> reviews = reviewRepository.findAll();
        Assertions.assertThat(reviews.size()).isGreaterThan(1);

    }

}
