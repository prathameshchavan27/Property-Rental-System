package com.prs.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.prs.pojos.Property;


public interface PropertyService {
	void addProperty( String title,String description,String address,String city,double rent,Long ownerId,List<MultipartFile> images) throws IOException;

	Optional<Property> getPropertyById(Long id);

	List<Property> getAllProperties();

	List<Property> searchProperty(String city);

	String deleteProperty(Long id);

	List<Property> getPropertiesByLid(Long id);
}
