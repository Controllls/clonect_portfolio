package com.clonect.clothes.api;

import com.clonect.clothes.domain.Feed;
import com.clonect.clothes.domain.Member;
import com.clonect.clothes.domain.clothes.Clothes;
import com.clonect.clothes.service.ClothesService;
import com.clonect.clothes.service.FeedService;
import com.clonect.clothes.service.MemberService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoadApi {
    private final MemberService memberService;
    private final ClothesService clothesService;
    private final FeedService feedService;

    @GetMapping("/api/load/{identity}")
    public JSONObject load(@PathVariable("identity") String identity) {
        try {
            /**
             멤버정보
             */
            List<Member> members = memberService.findByIdentity(identity);
            Member member = members.get(0);
            MemberLoadDTO memberLoadDTO = new MemberLoadDTO(member.getName(), member.getEmail(), member.getPassword()
                    , member.getPhone(), member.getGender(), member.getBirth());

            /**
            옷 피드 정보
             */
            List<Feed> feeds = feedService.findFeedsByUserIdentity(identity);

//            List<Clothes> clothes = clothesService.findClothesByUserIdentity(identity);

//            List<Clothes> clothes = clothesService.findClothesByFeedId()

            List<FeedLoadDTO> selectFeed = feeds.stream()
                    .map(m -> new FeedLoadDTO(m))
                    .collect(Collectors.toList());

            List<ClothesLoadDTO> clothesLoadDTOS = new ArrayList<>();
            for (FeedLoadDTO feedLoadDTO : selectFeed) {
                List<Clothes> clothes = clothesService.findClothesByFeedId(feedLoadDTO.getId());
                ClothesLoadDTO clothesLoadDTO = new ClothesLoadDTO(feedLoadDTO.getDate(), clothes.get(0).getUrl(), clothes.get(1).getUrl()
                        , clothes.get(2).getUrl(), clothes.get(3).getUrl(), clothes.get(4).getUrl(), clothes.get(5).getUrl());
                clothesLoadDTOS.add(clothesLoadDTO);
            }


            //        List<ClothesLoadDTO> selectClotehs = selectFeed.stream()
            //                .map(m -> new ClothesLoadDTO(m))
            //                .collect(Collectors.toList());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("memberinfo", memberLoadDTO);
            jsonObject.put("clothesinfo", clothesLoadDTOS);
            return jsonObject;
        }catch (IndexOutOfBoundsException e){
            log.info("회원없음");
            return null;
        }
    }

    @Data
    @AllArgsConstructor
    static class MemberLoadDTO{
//        @JsonProperty
//        private String date;
        @JsonProperty
        private String name;
        @JsonProperty
        private String email;
        @JsonProperty
        private String password;
        @JsonProperty
        private String phone;
        @JsonProperty
        private String gender;
        @JsonProperty
        private String birth;

    }

    @Data
    static class FeedLoadDTO{
        //        @JsonProperty
//        private String date;
        private String date;
        private Long id;
        public FeedLoadDTO(Feed feed) {
//            Pattern pattern = Pattern.compile("\\d{4}_\\d{2}_\\d{2}");

            String[] str = feed.getFeed_image().getImagePath().split("/");
            this.date = str[str.length-1].split("\\.")[0];

            this.id = feed.getId();
        }
    }

    @Data
    @AllArgsConstructor
    static class ClothesLoadDTO{
        @JsonProperty
        private String date;

        private String outer_url;
        private String top_url;
        private String bottom_url;
        private String bag_url;
        private String accessory_url;
        private String shoes_url;
//
////        Clothes.
//        public ClothesLoadDTO(Clothes clothes) {
//            this.date = clothes.getDate();
//            clothes.getUrl()

//        }
    }

}
