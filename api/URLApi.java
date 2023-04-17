package com.clonect.clothes.api;

import com.clonect.clothes.exception.NotFoundImageException;
import com.clonect.clothes.service.AppleService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class URLApi {

    private final AppleService appleService;

    @GetMapping("/key/{key}")
    public String signup(@PathVariable("key") String key) {
        return appleService.userIdFromApple(key);

    }
//    @GetMapping("/key/pk")
//    public String pk(){
//        try {
//            return appleService.makeClientSecret();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @GetMapping("/api/topURL/{userEmail}/{url}")
    public ResponseEntity<Resource> getImageByUserId(@PathVariable("userEmail") String userEmail,
                                                     @PathVariable("url") String url , @PathVariable("index") int index) {
        try {
//            List<Feed> findFeeds = feedService.findFeedsByUserEmail(userEmail);
//
//            List<FeedApiController.FeedImageDto> selectFeed = findFeeds.stream()
//                    .map(m -> new FeedApiController.FeedImageDto(m))
//                    .collect(Collectors.toList());

//            FeedApiController.FeedImageDto feedImage = selectFeed.get(index);
            String path = "C:\\Users\\USER\\Desktop\\images\\" + userEmail + "\\top\\";

            String RealURL = path + url;

            FileSystemResource resource = new FileSystemResource(RealURL);
            if (!resource.exists()) {
                throw new NotFoundImageException();
            }
            HttpHeaders header = new HttpHeaders();
            Path filePath = null;
            filePath = Paths.get(RealURL);
            header.add("Content-Type", Files.probeContentType(filePath));

            return  new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundImageException();
        }
    }

}
