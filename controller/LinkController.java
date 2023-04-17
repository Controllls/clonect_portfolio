//package com.clonect.clothes.controller;
//
//import com.clonect.clothes.domain.Feed;
//import com.clonect.clothes.service.FeedService;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//@RequiredArgsConstructor
//public class LinkController {
//    private final FeedService feedService;
//
//    @GetMapping("/api/link/URLs/{member_email}")
//    private void get_link_URLs(String email){
//        List<Feed> feeds = feedService.findFeedsByUserEmail(email);
//
//        List<LinkURLsDto> collect = feeds.stream()
//                .map(m -> new LinkURLsDto(m))
//                .collect(Collectors.toList());
//
////        collect
//
//
//    }
//
//    @Data
//    @AllArgsConstructor
//    static class LinkURLsDto {
//        private Long id;
//
//        private String URLs;
//
//        public LinkURLsDto(Feed feed) {
//            id = feed.getId();
//            URLs = feed.getLink().getURLs();
//        }
//
//    }
//}
