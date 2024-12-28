package com.hmsapp.service;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.Reviews;
import com.hmsapp.entity.User;
import com.hmsapp.exception.ResourceNotFound;
import com.hmsapp.payload.ReviewsDTO;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.repository.ReviewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final PropertyRepository propertyRepository;
    private final ReviewsRepository reviewsRepository;
    private ModelMapper modelMapper;

    public ReviewService(PropertyRepository propertyRepository,
                         ReviewsRepository reviewsRepository,
                         ModelMapper modelMapper){
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
        this.modelMapper = modelMapper;
    }

    public ReviewsDTO addReviews(ReviewsDTO reviewsDTO,
                           Long propertyId,
                           User user
                           ){
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ResourceNotFound("Property not found with ID: " + propertyId));
        Reviews reviewStatus = reviewsRepository.findByPropertyAndUser(property, user);

        if (reviewStatus != null) {
            throw new IllegalStateException("Review already given for this property and user.");
        }

        Reviews reviews = modelMapper.map(reviewsDTO, Reviews.class);

        reviews.setProperty(property);
        reviews.setUser(user);
        Reviews savedReviews = reviewsRepository.save(reviews);

        ReviewsDTO savedReviewsDTO = modelMapper.map(savedReviews, ReviewsDTO.class);
        savedReviewsDTO.getUser().setPassword(null);

        return savedReviewsDTO;
    }

    public List<ReviewsDTO> viewMyReviews(User user){
        List<Reviews> reviews = reviewsRepository.findByUser(user);
        List<ReviewsDTO> reviewsDTO = reviews.stream()
                .map(reviews1 -> {
                    reviews1.getUser().setPassword(null);
                    return modelMapper.map(reviews1, ReviewsDTO.class);
                })
                .collect(Collectors.toList());
        return reviewsDTO;
    }
}
