package com.hmsapp.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.hmsapp.entity.Reviews;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    private String mobile;

    private String role;

    private Set<Reviews> reviews = new LinkedHashSet<>();
}
