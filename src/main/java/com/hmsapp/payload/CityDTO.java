package com.hmsapp.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CityDTO {

    private Long id;
    private String name;
}
