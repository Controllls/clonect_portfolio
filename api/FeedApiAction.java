package com.clonect.clothes.api;

import com.clonect.clothes.service.ClothesService;
import com.clonect.clothes.service.FeedService;
import com.clonect.clothes.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FeedApiAction {
    private final FeedService feedService;
    private final ClothesService clothesService;
    /**
    이미지 저장 및 링크 저장

    input data :

    String email ,
    String top_URL,
    String bottom_URL,
    String outer_URL,
    String shoes_URL,
    String bag_URL,
    Multifile accessory_URL iamge

    이미지 이름 : 오늘 날짜 ex: 2022_06_03
     */

    @PostMapping("/save/feed")
    public FeedResponse SaveFeed(FeedRequest feedRequest) {

        //파일 저장 폴더 경로 생성
        String path = "/home/ec2-user/images/" + feedRequest.getIdentity() + "/feed/";
        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
                log.info("폴더 저장 실패");
                e.getStackTrace();
            }
        }
        //파일 경로
        Path imagePath = Paths.get(path + feedRequest.getImage().getOriginalFilename());
        //사진 서버에 저장
        try {
            Files.write(imagePath, feedRequest.getImage().getBytes());
        } catch (Exception e) {
            log.info("사진 저장 실패");
        }
        //DB 피드 저장
        Long feed_id = feedService.feed(
                feedRequest.getIdentity(),
                feedRequest.getImage().getOriginalFilename(),
                imagePath.toString());


        //outer
        path = "/home/ec2-user/images/" + feedRequest.getIdentity() + "/outer/";
        Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
                log.info("폴더 저장 실패");
                e.getStackTrace();
            }
        }
        //파일 경로
        imagePath = Paths.get(path + feedRequest.getImage().getOriginalFilename());
        //사진 서버에 저장
        try {
            Files.write(imagePath, feedRequest.getImage().getBytes());
        } catch (Exception e) {
            log.info("사진 저장 실패");
        }
        //DB 옷 저장
        clothesService.clothes_Outer(feedRequest.getIdentity() ,feed_id,null , feedRequest.getOuter_URL(),feedRequest.getImage().getOriginalFilename()
                , imagePath.toString());

        //top
        path = "/home/ec2-user/images/" + feedRequest.getIdentity() + "/top/";
        Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
                log.info("폴더 저장 실패");
                e.getStackTrace();
            }
        }
        //파일 경로
        imagePath = Paths.get(path + feedRequest.getImage().getOriginalFilename());
        //사진 서버에 저장
        try {
            Files.write(imagePath, feedRequest.getImage().getBytes());
        } catch (Exception e) {
            log.info("사진 저장 실패");
        }
        //DB 옷 저장
        clothesService.clothes_Top(feedRequest.getIdentity() ,feed_id,null , feedRequest.getTop_URL(),feedRequest.getImage().getOriginalFilename()
                , imagePath.toString());

        //bottom
        path = "/home/ec2-user/images/" + feedRequest.getIdentity() + "/bottom/";
        Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
                log.info("폴더 저장 실패");
                e.getStackTrace();
            }
        }
        //파일 경로
        imagePath = Paths.get(path + feedRequest.getImage().getOriginalFilename());
        //사진 서버에 저장
        try {
            Files.write(imagePath, feedRequest.getImage().getBytes());
        } catch (Exception e) {
            log.info("사진 저장 실패");
        }
        //DB 옷 저장
        clothesService.clothes_Bottom(feedRequest.getIdentity() ,feed_id,null , feedRequest.getBottom_URL(),feedRequest.getImage().getOriginalFilename()
                , imagePath.toString());

        //bag
        path = "/home/ec2-user/images/" + feedRequest.getIdentity() + "/bag/";
        Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
                log.info("폴더 저장 실패");
                e.getStackTrace();
            }
        }
        //파일 경로
        imagePath = Paths.get(path + feedRequest.getImage().getOriginalFilename());
        //사진 서버에 저장
        try {
            Files.write(imagePath, feedRequest.getImage().getBytes());
        } catch (Exception e) {
            log.info("사진 저장 실패");
        }
        //DB 옷 저장
        clothesService.clothes_Bag(feedRequest.getIdentity() ,feed_id,null , feedRequest.getBag_URL(),feedRequest.getImage().getOriginalFilename()
                , imagePath.toString());

        //accessory
        path = "/home/ec2-user/images/" + feedRequest.getIdentity() + "/accessory/";
        Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
                log.info("폴더 저장 실패");
                e.getStackTrace();
            }
        }
        //파일 경로
        imagePath = Paths.get(path + feedRequest.getImage().getOriginalFilename());
        //사진 서버에 저장
        try {
            Files.write(imagePath, feedRequest.getImage().getBytes());
        } catch (Exception e) {
            log.info("사진 저장 실패");
        }
        //DB 옷 저장
        clothesService.clothes_Accessory(feedRequest.getIdentity() ,feed_id,null , feedRequest.getAccessory_URL(),feedRequest.getImage().getOriginalFilename()
                , imagePath.toString());

        //shoes
        path = "/home/ec2-user/images/" + feedRequest.getIdentity() + "/shoes/";
        Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
                log.info("폴더 저장 실패");
                e.getStackTrace();
            }
        }
        //파일 경로
        imagePath = Paths.get(path + feedRequest.getImage().getOriginalFilename());
        //사진 서버에 저장
        try {
            Files.write(imagePath, feedRequest.getImage().getBytes());
        } catch (Exception e) {
            log.info("사진 저장 실패");
        }
        //DB 옷 저장
        clothesService.clothes_Shoes(feedRequest.getIdentity() ,feed_id,null , feedRequest.getShoes_URL(),feedRequest.getImage().getOriginalFilename()
                , imagePath.toString());

        return new FeedResponse(feedRequest.getIdentity());
    }


    @Data//왜 스태틱
    @AllArgsConstructor
    private class FeedRequest {
        //피드
        private String identity;
        private MultipartFile image;
        //옷
        private String top_URL;
        private String bottom_URL;
        private String outer_URL;
        private String shoes_URL;
        private String bag_URL;
        private String accessory_URL;


    }

    @Data
    @AllArgsConstructor
    private class FeedResponse {
        private String identity;

    }

}
