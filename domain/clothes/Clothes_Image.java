package com.clonect.clothes.domain.clothes;


import com.clonect.clothes.domain.clothes.Clothes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Clothes_Image {

    @Id
    @GeneratedValue
    @Column(name = "clothes_image_id")
    private Long id;

    private String file_name;

    private String imagePath;

    @OneToOne(mappedBy = "clothes_image")
    private Clothes clothes;


}