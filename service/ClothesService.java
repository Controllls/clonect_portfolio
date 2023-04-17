package com.clonect.clothes.service;

import com.clonect.clothes.domain.Feed;
import com.clonect.clothes.domain.Member;
import com.clonect.clothes.domain.clothes.*;
import com.clonect.clothes.repository.ClothesRepository;
import com.clonect.clothes.repository.FeedRepository;
import com.clonect.clothes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClothesService {

    private final ClothesRepository clothesRepository;
    private final MemberRepository memberRepository;
    private final FeedRepository feedRepository;
    //저장


    public Long join(Clothes clothes){

        clothesRepository.save(clothes);
        return clothes.getId(); //em이 Id가져와서
    }

    //외투 저장
    @Transactional
    public Long clothes_Outer(String identity ,Long id,String name , String url, String image_name , String imagePath){
        Outer outer = new Outer();
        outer.setName(name);
        outer.setUrl(url);
        outer.setDate(LocalDateTime.now());

        List<Member> members = memberRepository.findByIdentity(identity);
        Member member = members.get(0);
        outer.setMember(member);

        Feed feed = feedRepository.findOne(id);
        outer.setFeed(feed);

        Clothes_Image clothes_image = new Clothes_Image();
        clothes_image.setFile_name(image_name);
        clothes_image.setImagePath(imagePath);

        outer.setClothes_image(clothes_image);

        clothesRepository.save(outer);

        return outer.getId();
    }

    @Transactional
    public Long clothes_Top(String identity ,Long id,String name , String url, String image_name , String imagePath){
        Top top = new Top();
        top.setName(name);
        top.setUrl(url);
        top.setDate(LocalDateTime.now());

        List<Member> members = memberRepository.findByIdentity(identity);
        Member member = members.get(0);
        top.setMember(member);

        Feed feed = feedRepository.findOne(id);
        top.setFeed(feed);

        Clothes_Image clothes_image = new Clothes_Image();
        clothes_image.setFile_name(image_name);
        clothes_image.setImagePath(imagePath);

        top.setClothes_image(clothes_image);

        clothesRepository.save(top);

        return top.getId();
    }

    @Transactional
    public Long clothes_Bottom(String identity,Long id,String name , String url, String image_name , String imagePath){
        Bottom outer = new Bottom();
        outer.setName(name);
        outer.setUrl(url);
        outer.setDate(LocalDateTime.now());

        List<Member> members = memberRepository.findByIdentity(identity);
        Member member = members.get(0);
        outer.setMember(member);

        Feed feed = feedRepository.findOne(id);
        outer.setFeed(feed);

        Clothes_Image clothes_image = new Clothes_Image();
        clothes_image.setFile_name(image_name);
        clothes_image.setImagePath(imagePath);

        outer.setClothes_image(clothes_image);

        clothesRepository.save(outer);

        return outer.getId();
    }

    @Transactional
    public Long clothes_Accessory(String identity ,Long id,String name , String url, String image_name , String imagePath){
        Accessory outer = new Accessory();
        outer.setName(name);
        outer.setUrl(url);
        outer.setDate(LocalDateTime.now());

        List<Member> members = memberRepository.findByIdentity(identity);
        Member member = members.get(0);
        outer.setMember(member);

        Feed feed = feedRepository.findOne(id);
        outer.setFeed(feed);

        Clothes_Image clothes_image = new Clothes_Image();
        clothes_image.setFile_name(image_name);
        clothes_image.setImagePath(imagePath);

        outer.setClothes_image(clothes_image);

        clothesRepository.save(outer);

        return outer.getId();
    }

    @Transactional
    public Long clothes_Bag(String identity ,Long id,String name , String url, String image_name , String imagePath){
        Bag outer = new Bag();
        outer.setName(name);
        outer.setUrl(url);
        outer.setDate(LocalDateTime.now());

        List<Member> members = memberRepository.findByIdentity(identity);
        Member member = members.get(0);
        outer.setMember(member);

        Feed feed = feedRepository.findOne(id);
        outer.setFeed(feed);

        Clothes_Image clothes_image = new Clothes_Image();
        clothes_image.setFile_name(image_name);
        clothes_image.setImagePath(imagePath);

        outer.setClothes_image(clothes_image);

        clothesRepository.save(outer);

        return outer.getId();
    }

    @Transactional
    public Long clothes_Shoes(String identity ,Long id ,String name , String url, String image_name , String imagePath){
        Shoes outer = new Shoes();
        outer.setName(name);
        outer.setUrl(url);
        outer.setDate(LocalDateTime.now());

        List<Member> members = memberRepository.findByIdentity(identity);
        Member member = members.get(0);
        outer.setMember(member);

        Clothes_Image clothes_image = new Clothes_Image();
        clothes_image.setFile_name(image_name);
        clothes_image.setImagePath(imagePath);

        Feed feed = feedRepository.findOne(id);
        outer.setFeed(feed);

        outer.setClothes_image(clothes_image);

        clothesRepository.save(outer);

        return outer.getId();
    }

    public List<Clothes> findClothesByUserIdentity(String identity){
        return clothesRepository.findByUserIdentity(identity);
    }

    public List<Clothes> findClothesByFeedId(Long id){
        return clothesRepository.findByFeedId(id);
    }

    @Transactional
    public void update_Outer(Long id, String name , Long size, String price , String brand ) {
        Clothes clothes = clothesRepository.findOne(id);
        Outer outer = (Outer)clothes;
        outer.setSize(size);

        outer.setName(name);
        outer.setPrice(price);
        outer.setBrand(brand);


        clothesRepository.save(clothes);
    }

    @Transactional
    public void update_Top(Long id, String name , Long size, String price , String brand ) {
        Clothes clothes = clothesRepository.findOne(id);
        Top outer = (Top)clothes;
        outer.setSize(size);

        outer.setName(name);
        outer.setPrice(price);
        outer.setBrand(brand);


        clothesRepository.save(clothes);
    }

    @Transactional
    public void update_Bottom(Long id, String name , Long size, String price , String brand ) {
        Clothes clothes = clothesRepository.findOne(id);
        Bottom outer = (Bottom)clothes;
        outer.setSize(size);

        outer.setName(name);
        outer.setPrice(price);
        outer.setBrand(brand);


        clothesRepository.save(clothes);
    }

    @Transactional
    public void update_Accessory(Long id, String name , Long size, String price , String brand ) {
        Clothes clothes = clothesRepository.findOne(id);
        Accessory outer = (Accessory)clothes;

        outer.setName(name);
        outer.setPrice(price);
        outer.setBrand(brand);


        clothesRepository.save(clothes);
    }

    @Transactional
    public void update_Bag(Long id, String name , Long size, String price , String brand ) {
        Clothes clothes = clothesRepository.findOne(id);
        Bag outer = (Bag)clothes;

        outer.setName(name);
        outer.setPrice(price);
        outer.setBrand(brand);

        clothesRepository.save(clothes);
    }

    @Transactional
    public void update_Shoes(Long id, String name , Long size, String price , String brand ) {
        Clothes clothes = clothesRepository.findOne(id);
        Shoes outer = (Shoes)clothes;
        outer.setSize(size);

        outer.setName(name);
        outer.setPrice(price);
        outer.setBrand(brand);


        clothesRepository.save(clothes);
    }

}
