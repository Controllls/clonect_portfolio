package com.clonect.clothes.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Feed_Image {

    @Id
    @GeneratedValue
    @Column(name = "feed_image_id")
    private Long id;

    private String file_name;

    private String imagePath;

    @OneToOne(mappedBy = "feed_image")
    private Feed feed;

}
