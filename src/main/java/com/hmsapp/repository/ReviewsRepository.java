package com.hmsapp.repository;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.Reviews;
import com.hmsapp.entity.User;
import com.hmsapp.payload.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    public List<Reviews> findByUser(User user);

    Reviews findByPropertyAndUser(Property property, User user);
}