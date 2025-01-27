package com.prs.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prs.pojos.Property;



@RestController
//@RequestMapping("/landlord/properties")
public class PropertiesController {

	@Autowired
	private com.prs.service.PropertyService propertyService;
    
    @PostMapping("/landlord/properties/add")
    public ResponseEntity<?> addPropertyWithImages(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("rent") double rent,
            @RequestParam("ownerId") Long ownerId,
            @RequestPart("images") List<MultipartFile> images) {
    	
    	
        try {
            // Save Property
        	propertyService.addProperty(title, description, address, city, rent, ownerId, images);
            
            return ResponseEntity.ok("Property and images added successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error occurred while saving property or images: " + e.getMessage());
        }
    }
    
    @GetMapping("/tenant/properties")
    public ResponseEntity<?> getAllProperties(){
    	return ResponseEntity.status(HttpStatus.OK).body(propertyService.getAllProperties());
    }
    
//
    @GetMapping("/landlord/property/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable Long id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        
        if (property.isPresent()) {
            return ResponseEntity.ok(property.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/landlord/properties/{id}")
    public ResponseEntity<?> getPropertiesByLid(@PathVariable Long id){
    	return ResponseEntity.status(HttpStatus.OK).body(propertyService.getPropertiesByLid(id));
    }
    
    @GetMapping("/tenant/search")
    public ResponseEntity<?> searchProperty(@RequestParam String city){
    	return ResponseEntity.status(HttpStatus.OK).body(propertyService.searchProperty(city));
    }
    
    @DeleteMapping("/landlord/properties/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id){
    	return ResponseEntity.status(HttpStatus.OK).body(propertyService.deleteProperty(id));
    }
    
    

    
}
