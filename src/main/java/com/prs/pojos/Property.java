package com.prs.pojos;



import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "properties")
//@Getter
//@Setter
@ToString
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String address;
    private String city;
    private double rent;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "owner", nullable = false) // Foreign key column
    private User owner;

    @Transient
    private Long ownerId; // Used only for accepting input from the API

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "propertyId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Images> images;

    // Default constructor
//    public Property() {}
    // Constructor to initialize Property
    public Property(String title, String description, String address, String city, double rent, User owner) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.city = city;
        this.rent = rent;
        this.owner = owner; // Assign the User object directly
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Setters and Getters (Use @Getter and @Setter with Lombok if available)
//    public Long getOwnerId() {
//        return ownerId;
//    }
//
//    public void setOwnerId(Long ownerId) {
//        this.ownerId = ownerId;
//        this.owner = new User(ownerId); // Create User object for the relationship
//    }

    // Other setters and getters...


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public double getRent() {
		return rent;
	}


	public void setRent(double rent) {
		this.rent = rent;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public List<Images> getImages() {
		return images;
	}


	public void setImages(List<Images> images) {
		this.images = images;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
}
