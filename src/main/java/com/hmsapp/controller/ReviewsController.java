package com.hmsapp.controller;

import com.hmsapp.entity.User;
import com.hmsapp.payload.ReviewsDTO;
import com.hmsapp.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/review")
public class ReviewsController {

    private ReviewService reviewService;

    public ReviewsController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/addReviews")
    public ResponseEntity<ReviewsDTO> addReview(
            @RequestBody ReviewsDTO reviewsDTO,
            @RequestParam Long propertyId,
            @AuthenticationPrincipal User user
    ){
        ReviewsDTO reviewsDTO1 = reviewService.addReviews(reviewsDTO, propertyId, user);
        return new ResponseEntity<>(reviewsDTO1, HttpStatus.OK);
    }

    @GetMapping("/user/reviews")
    public ResponseEntity<List<ReviewsDTO>> viewMyReviews(
            @AuthenticationPrincipal User user
    ){
        List<ReviewsDTO> listReviewsDTO = reviewService.viewMyReviews(user);
        return new ResponseEntity<>(listReviewsDTO, HttpStatus.OK);
    }
}
