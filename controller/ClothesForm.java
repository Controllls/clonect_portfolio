package com.clonect.clothes.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter @Setter
public class ClothesForm {

    private Long id;

    private String email;

    private String name;
    private Long size;

    private File image;

    private String file_name;

    private String imagePath;
}
