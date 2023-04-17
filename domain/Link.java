package com.clonect.clothes.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Link {
    @Id @GeneratedValue
    @Column(name = "link_id")
    private Long id;

    private String top_URL;
    private String bottom_URL;
    private String outer_URL;
    private String accessory_URL;
    private String shoes_URL;
    private String bag_URL;


//    @OneToOne(mappedBy = "link")
//    private Feed feed;
}
