package com.clonect.clothes.domain.clothes;

import com.clonect.clothes.domain.Feed;
import com.clonect.clothes.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothes_id") //pk이름
    private Long id;
    private String name;
    private String price;
    private String brand;
    private String url;
    private LocalDateTime date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_image_id" )
    private Clothes_Image clothes_image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

}
