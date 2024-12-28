package com.hmsapp.service;

import com.hmsapp.entity.Country;
import com.hmsapp.payload.CountryDTO;
import com.hmsapp.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    private final ModelMapper modelMapper;

    public CountryService(CountryRepository countryRepository,
                       ModelMapper modelMapper){
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    public CountryDTO addCountry(CountryDTO countryDTO){
        Country country = new Country();

        country.setId(countryDTO.getId());
        country.setName(countryDTO.getName());

        Country country1 = countryRepository.save(country);

        return modelMapper.map(country1, CountryDTO.class);
    }

    public void deleteCountry(Long id){
        countryRepository.deleteById(id);
    }

    public List<CountryDTO> getAllCountries(){
        List<Country> countries = countryRepository.findAll();
        List<CountryDTO> countriesDTO = countries.stream()
                .map(countriesElement -> modelMapper.map(countriesElement, CountryDTO.class))
                .collect(Collectors.toList());
        return countriesDTO;
    }
}
