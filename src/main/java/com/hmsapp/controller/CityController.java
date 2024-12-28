package com.hmsapp.controller;

import com.hmsapp.payload.CityDTO;
import com.hmsapp.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @PostMapping("/addCity")
    public ResponseEntity<CityDTO> addCity(
            @RequestBody CityDTO cityDTO
    ){
        CityDTO cityDTO1 = cityService.addCity(cityDTO);
        return new ResponseEntity<>(cityDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCity")
    public ResponseEntity<String> deleteCity(@RequestParam Long id){
        cityService.deleteCity(id);
        return new ResponseEntity<>("City is deleted", HttpStatus.OK);
    }

    @GetMapping("/fetchCities")
    public ResponseEntity<List<CityDTO>> getCities(){
        List<CityDTO> citiesDTO = cityService.getAllCities();
        return new ResponseEntity<>(citiesDTO, HttpStatus.OK);
    }
}