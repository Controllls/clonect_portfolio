//package com.clonect.clothes.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter @Setter
//public class Closet {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "closet_id")
//    private Long id;
//
//    @OneToOne(mappedBy = "closet")
//    private Member member;
//
////    @OneToMany(mappedBy = "closet")
////    private List<Clothes> clothes = new ArrayList<>();
//
////    @OneToMany(mappedBy = "clothes")
////    private List<Put> puts = new ArrayList<>();
//
//}
