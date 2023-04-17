package com.clonect.clothes.api;

import com.clonect.clothes.domain.Feed;
import com.clonect.clothes.domain.Member;
import com.clonect.clothes.exception.NotFoundImageException;
import com.clonect.clothes.service.FeedService;
import com.clonect.clothes.service.MemberService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FeedApiController {
    private final FeedService feedService;
/*
     피드 갯수 리턴
 */
    @GetMapping ("/api/checksize/{identity}")
    public int checkSize(@PathVariable("identity") String identity){
        List<Feed> feed = feedService.findFeedsByUserIdentity(identity);
        return  feed.size();
    }
    /*
    identity 별로 이미지 가져오기

    index 반복
    //query a.member.identity 해야함 ㅡ ㅡ ㅡ ㅡ ㅡㅡ ㅡ
    //todo 한번에 보내는 법
     */
//    @GetMapping("/user/feedImage/{userIdentity}/{index}")
//    public ResponseEntity<Resource> getImageByUserIdentity(@PathVariable("userIdentity") String userIdentity,
//                                                           @PathVariable("index") int index) {
//        List<Feed> findFeeds = feedService.findFeedsByUserIdentity(userIdentity);
//
//        List<FeedImageDto> selectFeed = findFeeds.stream()
//                .map(m -> new FeedImageDto(m))
//                .collect(Collectors.toList());
//
//        FeedImageDto feedImage = selectFeed.get(index);
//        try {
//            FileSystemResource resource = new FileSystemResource(feedImage.getImagePath());
//            if (!resource.exists()) {
//                throw new NotFoundImageException();
//            }
//            HttpHeaders header = new HttpHeaders();
//            Path filePath = null;
//            filePath = Paths.get(feedImage.getImagePath());
//            header.add("Content-Type", Files.probeContentType(filePath));
//
//            return  new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
//        } catch (Exception e) {
//            throw new NotFoundImageException();
//        }
//    }

    @Data
    @AllArgsConstructor
    static class FeedImageDto {
        private String imagePath;

        public FeedImageDto(Feed feed) {
            imagePath = feed.getFeed_image().getImagePath();
        }

    }

    /*
    상의 가져오기
     */



    /*
    링크들 가져오기
     */
//    @GetMapping("api/link/{feed}")
//    public List<FeedLinkDto> getLink(@PathVariable("feed") Long feed_id){
//        List<Feed> findFeeds = feedService.findFeedsByUserIdentity(identity);
//
//        List<FeedLinkDto> selectFeed = findFeeds.stream()
//                .map(m -> new FeedLinkDto(m))
//                .collect(Collectors.toList());
//
//        return selectFeed;
//    }


    @GetMapping("/user/feedImage/{userIdentity}/{date}")
    public ResponseEntity<Resource> getImage(@PathVariable("userIdentity") String userIdentity,
                                             @PathVariable("date") String date) {

        List<Feed> findFeeds = feedService.findFeedsByUserIdentity(userIdentity);

        List<FeedImageDto> selectFeed = findFeeds.stream()
                .map(m -> new FeedImageDto(m))
                .collect(Collectors.toList());

        FeedImageDto feedImage = null;

        for (FeedImageDto feed : selectFeed){
            if (feed.getImagePath().contains(date)){
                feedImage = feed;
            }
        }

        try {
            FileSystemResource resource = new FileSystemResource(feedImage.getImagePath());
            if (!resource.exists()) {
                throw new NotFoundImageException();
            }
            HttpHeaders header = new HttpHeaders();
            Path filePath = null;
            filePath = Paths.get(feedImage.getImagePath());
            header.add("Content-Type", Files.probeContentType(filePath));

            return  new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundImageException();
        }
    }


}
