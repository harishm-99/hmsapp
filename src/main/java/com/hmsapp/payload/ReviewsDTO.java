package com.hmsapp.payload;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ReviewsDTO {

    private Long id;

    private Integer rating;

    private String description;

    private Property property;

    private User user;

}
