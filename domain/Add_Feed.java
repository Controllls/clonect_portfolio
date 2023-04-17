//package com.clonect.clothes.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Setter @Getter
//public class Add_Feed {
//    @Id
//    @GeneratedValue
//    @Column(name = "add_feed_id") //pk이름
//    private Long id;
//
//    private LocalDate date;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "feed_id")
//    private Feed feed;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "link_id")
//    private Link link;
//
//
//
//
//
//}
