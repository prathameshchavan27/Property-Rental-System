package com.prs.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prs.dao.ImageRepository;
import com.prs.dao.PropertyRepository;
import com.prs.pojos.Images;
import com.prs.pojos.Property;
import com.prs.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

	@Autowired
    private PropertyRepository propertyRepository;
	 
	@Autowired
    private ImageRepository imageRepository;

	private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

	
	@Override
	public void addProperty(String title, String description, String address, String city, double rent, Long ownerId,
			List<MultipartFile> images) throws IOException {
		// TODO Auto-generated method stub
		Property property = new Property(title,description, address,city,rent,new User(ownerId));
//        
        property = propertyRepository.save(property);

        // Save Images
        List<Images> imageList = new ArrayList<>();
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        for (MultipartFile file : images) {
//            
            String filePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();
            file.transferTo(new File(filePath));


            Images image = new Images();
            image.setPropertyId(property.getId());
            image.setImageUrl(file.getOriginalFilename());
            image.setUploadedAt(LocalDateTime.now());
            imageList.add(image);
        }
        imageRepository.saveAll(imageList);

		
	}


	@Override
	public Optional<Property> getPropertyById(Long id) {
		// TODO Auto-generated method stub
		return propertyRepository.findById(id);
	}


	@Override
	public List<Property> getAllProperties() {
		// TODO Auto-generated method stub
		return propertyRepository.findAll();
	}


	@Override
	public List<Property> searchProperty(String city) {
		// TODO Auto-generated method stub
		return propertyRepository.findByCity(city);
	}


	@Override
	public String deleteProperty(Long id) {
		// TODO Auto-generated method stub
		if(propertyRepository.existsById(id)) {			
			propertyRepository.deleteById(id);
			return "Deleted Successfully";
		}
		return "Delete Failed";
	}


	@Override
	public List<Property> getPropertiesByLid(Long id) {
		// TODO Auto-generated method stub
		return propertyRepository.findByOwner(new User(id));
	}
	
}
