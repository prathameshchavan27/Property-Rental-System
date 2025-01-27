package com.prs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.pojos.Property;
import com.prs.pojos.User;




public interface PropertyRepository extends JpaRepository<Property, Long> {
	List<Property> findByCity(String city);
	List<Property> findByOwner(User owner);
}
