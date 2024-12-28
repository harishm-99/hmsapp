package com.hmsapp.controller;

import com.hmsapp.payload.CountryDTO;
import com.hmsapp.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<CountryDTO> addCountry(
            @RequestBody CountryDTO countryDTO
    ){
        CountryDTO countryDTO1 = countryService.addCountry(countryDTO);
        return new ResponseEntity<>(countryDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCountry")
    public ResponseEntity<String> deleteCountry(@RequestParam Long id){
        countryService.deleteCountry(id);
        return new ResponseEntity<>("Country is deleted", HttpStatus.OK);
    }

    @GetMapping("/fetchCountries")
    public ResponseEntity<List<CountryDTO>> getCountries(){
        List<CountryDTO> countriesDTO = countryService.getAllCountries();
        return new ResponseEntity<>(countriesDTO, HttpStatus.OK);
    }
}




