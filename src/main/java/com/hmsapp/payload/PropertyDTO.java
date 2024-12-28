package com.hmsapp.payload;

import com.hmsapp.entity.City;
import com.hmsapp.entity.Country;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PropertyDTO {

    private Long id;
    private String name;
    private String noOfGuests;
    private String noOfBedrooms;
    private String noOfBathrooms;

    private Country country;
    private City city;

}
