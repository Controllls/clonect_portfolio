package com.clonect.clothes.service;

import com.clonect.clothes.domain.Feed;
import com.clonect.clothes.domain.Feed_Image;
import com.clonect.clothes.domain.Link;
import com.clonect.clothes.domain.Member;
import com.clonect.clothes.repository.FeedRepository;
import com.clonect.clothes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedService {

    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Long feed(String identity , String image_name , String imagePath){
        //이거 어케 member로 리턴받지..
        //identity로 멤버를찾자
        List<Member> members = memberRepository.findByIdentity(identity);
        Member member = members.get(0);

        //피드 이미지 데이터 입력
        Feed_Image feed_image = new Feed_Image();
        feed_image.setFile_name(image_name);
        feed_image.setImagePath(imagePath);
        //피드 생성
        Feed feed = Feed.createFeed(member , feed_image);
        //피드 저장
        feedRepository.save(feed);

        return feed.getId();
    }


    public List<Feed> findFeeds(){
        return feedRepository.findAII();
    }

    public Feed findOne(Long feedId){
        return feedRepository.findOne(feedId);
    }

    public List<Feed> findFeedsByUserId(Long userId){
        return feedRepository.findByUserId(userId);
    }

    public List<Feed> findFeedsByUserEmail(String userEmail){
        return feedRepository.findByUserEmail(userEmail);
    }

    public List<Feed> findFeedsByUserIdentity(String identity){
        return feedRepository.findByUserIdentity(identity);
    }

//    @Transactional
//    public void updateItem(Long id, String name, int price) {
//        Item item = itemRepository.findOne(id);
//        item.setName(name);
//        item.setPrice(price);
//    }

}
