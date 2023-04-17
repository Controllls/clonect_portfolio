package com.clonect.clothes.domain;

import com.clonect.clothes.domain.clothes.Clothes;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Feed {
    @Id
    @GeneratedValue
    @Column(name = "feed_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "feed_image_id")
    private Feed_Image feed_image;

    @OneToMany(mappedBy = "feed")
    private List<Clothes> clothes = new ArrayList<>();

//    @OneToOne(fetch = FetchType.LAZY )
//    @JoinColumn(name = "feed_id")
//    private Link link;

    private LocalDateTime writeDate;
    //연관관계

    public void setMember(Member member){
        this.member = member;
        member.getFeeds().add(this);
    }

    public void setFeed_Image(Feed_Image feed_image) {
        this.feed_image = feed_image;
        feed_image.setFeed(this);
    }

    //생성자
    public static Feed createFeed(Member member , Feed_Image feed_image) {
        Feed feed = new Feed();
        feed.setMember(member);
        feed.setFeed_Image(feed_image);
        feed.setWriteDate(LocalDateTime.now());
        return feed;
    }
}
