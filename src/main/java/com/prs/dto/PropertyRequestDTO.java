package com.prs.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PropertyRequestDTO {
    private String title;
    private String description;
    private String address;
    private String city;
    private double rent;
    private int ownerId;

    private List<MultipartFile> images; // To handle multiple image uploads


}
