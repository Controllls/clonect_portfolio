package com.clonect.clothes.domain;

import com.clonect.clothes.domain.clothes.Clothes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private String gender;

    private String birth;
    @Column(unique = true)
    private String identity;

//    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Feed> feeds = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Clothes> clothes = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "closet_id")
//    private Closet closet;

    //연관관계
//    public void setCloset(Closet closet) {
//        this.closet = closet;
//        closet.setMember(this);
//    }

//    public static Member createMember(Long id, String name, String email, String password, String phone, String gender,
//                  String birth) {
//        Member member = new Member();
//        member.setId(id);
//        member.setName(name);
//        member.setPassword(password);
//        member.setEmail(email);
//        member.setPhone(phone);
//        member.setGender(gender);
//        member.setBirth(birth);
//
//        Closet closet = new Closet();
//        member.setCloset(closet);
//
//        return member;
//    }


}
