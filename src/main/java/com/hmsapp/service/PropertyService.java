package com.hmsapp.service;

import com.hmsapp.entity.Property;
import com.hmsapp.payload.PropertyDTO;
import com.hmsapp.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private ModelMapper modelMapper;

    public PropertyService(PropertyRepository propertyRepository, ModelMapper  modelMapper) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    public PropertyDTO addProperty(PropertyDTO propertyDTO){

        Property property = new Property();

        property.setId(propertyDTO.getId());
        property.setName(propertyDTO.getName());
        property.setNoOfBedrooms(propertyDTO.getNoOfBedrooms());
        property.setNoOfGuests(propertyDTO.getNoOfGuests());
        property.setNoOfBathrooms(propertyDTO.getNoOfBathrooms());

        property.setCity(propertyDTO.getCity());
        property.setCountry(propertyDTO.getCountry());

        Property property1 = propertyRepository.save(property);

        return modelMapper.map(property1, PropertyDTO.class);
    }

    public void deleteProperty(Long id){
        propertyRepository.deleteById(id);
    }

    @PutMapping("/updateProperty")
    public PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO){
        Property property = propertyRepository.findById(id).get();

        property.setName(propertyDTO.getName());
        property.setNoOfBedrooms(propertyDTO.getNoOfBedrooms());
        property.setNoOfGuests(propertyDTO.getNoOfGuests());
        property.setNoOfBathrooms(propertyDTO.getNoOfBathrooms());
        property.setCity(propertyDTO.getCity());
        property.setCountry(propertyDTO.getCountry());

        Property property1 = propertyRepository.save(property);

        return modelMapper.map(property1, PropertyDTO.class);
    }

    public List<PropertyDTO> getAllProperty(){
        List<Property> propertyList = propertyRepository.findAll();
        List<PropertyDTO> propertyListDTO = propertyList.stream()
                .map(propertyList1-> modelMapper.map(propertyList1, PropertyDTO.class))
                .collect(Collectors.toList());
        return propertyListDTO;
    }

    public List<PropertyDTO> searchProperty(String searchParam){
        List<Property> propertyList = propertyRepository.searchProperty(searchParam);
        List<PropertyDTO> propertyListDTO = propertyList.stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
        return propertyListDTO;
    }
}
