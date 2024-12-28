package com.hmsapp.service;

import com.hmsapp.entity.City;
import com.hmsapp.payload.CityDTO;
import com.hmsapp.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private CityRepository cityRepository;

    private final ModelMapper modelMapper;

    public CityService(CityRepository cityRepository,
                       ModelMapper modelMapper){
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    public CityDTO addCity(CityDTO cityDTO){
        City city = new City();

        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());

        City city1 = cityRepository.save(city);

        return modelMapper.map(city1, CityDTO.class);
    }

    public void deleteCity(Long id){
        cityRepository.deleteById(id);
    }

    public List<CityDTO> getAllCities(){
        List<City> cities = cityRepository.findAll();
        List<CityDTO> citiesDTO = cities.stream()
                .map(citiesElement -> modelMapper.map(citiesElement, CityDTO.class))
                .collect(Collectors.toList());
        return citiesDTO;
    }
}