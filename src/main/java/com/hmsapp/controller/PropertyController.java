package com.hmsapp.controller;

import com.hmsapp.payload.PropertyDTO;
import com.hmsapp.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDTO> addProperty(
        @RequestBody PropertyDTO propertyDTO
    ){
        PropertyDTO propertyDTO1 = propertyService.addProperty(propertyDTO);
        return new ResponseEntity<>( propertyDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProperty")
    public ResponseEntity<String> deleteProperty(
            @RequestParam Long id
    ){
        propertyService.deleteProperty(id);
        return new ResponseEntity<>("Property is deleted", HttpStatus.OK);
    }

    @PutMapping("/updateProperty")
    public ResponseEntity<PropertyDTO> updateProperty(
            @RequestBody PropertyDTO propertyDTO, @RequestParam Long id
    ){
        PropertyDTO propertyDTO1 = propertyService.updateProperty(id, propertyDTO);
        return new ResponseEntity<>(propertyDTO1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperty(){
        List<PropertyDTO> propertyList = propertyService.getAllProperty();
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @PostMapping("/addPropertyPhotos")
    public String addPropertyPhotos(){
        return "added photos";
    }

    @GetMapping("/searchProperty/{searchParam}")
    public ResponseEntity<List<PropertyDTO>> getProperty(
            @PathVariable String searchParam
    ){
        List<PropertyDTO> propertyList = propertyService.searchProperty(searchParam);
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }
}
