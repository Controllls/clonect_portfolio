package com.clonect.clothes.api;

import com.clonect.clothes.domain.clothes.Clothes;
import com.clonect.clothes.exception.NotFoundImageException;
import com.clonect.clothes.service.ClothesService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClothesApiController {
    private final ClothesService clothesService;

    /*
    url로 받은 사진 저장

    내부 저장소 에도 폴더 만들어서 관리해야할덧
    //todo 파일이름 어떡하지

    input data:
    multifile image
    identity String
    category String
    name String
    size Long
     */
//    @PostMapping("/save/clothes/outer")
//    public OuterResponse saveUserImage(OuterRequest request) {
//
//        String path = "/home/ec2-user/images/" + request.getIdentity() + "/outer/";
//
//        Path imagePath = Paths.get(path + request.getImage().getOriginalFilename());
//        try {
//            Files.write(imagePath, request.getImage().getBytes());
//        } catch (Exception e) {
//            log.info("옷 사진 저장 실패");
//        }
//        Long id = clothesService.clothes_Outer(request.getIdentity() ,request.getName(),
//                request.getUrl() , request.getImage().getOriginalFilename() ,imagePath.toString());
//
//        return new OuterResponse(id);
//    }

    @PostMapping("/update/outer")
    public void setOuter(updateRequest request){
        clothesService.update_Outer(request.getClothes_id(), request.getName(),
                request.getSize(), request.getPrice(), request.getBrand());
    }

    @PostMapping("/update/top")
    public void setTop(updateRequest request){
        clothesService.update_Top(request.getClothes_id(), request.getName(),
                request.getSize(), request.getPrice(), request.getBrand());
    }
    @PostMapping("/update/bottom")
    public void setBottom(updateRequest request){
        clothesService.update_Bottom(request.getClothes_id(), request.getName(),
                request.getSize(), request.getPrice(), request.getBrand());
    }
    @PostMapping("/update/shoes")
    public void setShoes(updateRequest request){
        clothesService.update_Shoes(request.getClothes_id(), request.getName(),
                request.getSize(), request.getPrice(), request.getBrand());
    }
    @PostMapping("/update/bag")
    public void setBag(updateRequest request){
        clothesService.update_Bag(request.getClothes_id(), request.getName(),
                request.getSize(), request.getPrice(), request.getBrand());
    }
    @PostMapping("/update/accessory")
    public void setAccessory(updateRequest request){
        clothesService.update_Accessory(request.getClothes_id(), request.getName(),
                request.getSize(), request.getPrice(), request.getBrand());
    }

    @Data
    @AllArgsConstructor
    static class updateRequest {
        private Long clothes_id;
        private Long size;
        private String name;
        private String price;
        private String brand;


    }

    @Data
    @AllArgsConstructor
    static class ClothesResponse {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    static class OuterResponse {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    static class ClothesRequest {
        private String identity;
        private String category;

        private String name;
        private Long size;
        private MultipartFile image;

    }

    @Data
    @AllArgsConstructor
    static class OuterRequest {
        private String identity;

        private String url;
        private String name;
        private MultipartFile image;
        private Long size;
        private LocalDateTime date;
    }
    /*
    서버에 있는 옷 냅다 가져오기

    identity category 파일명 ( 2020_01_01.확장자)


     */

//    @GetMapping("/clothes/{userIdentity}/{category}/{date}")
//    public ResponseEntity<Resource> getImageByUser(@PathVariable("userIdentity") String userIdentity,
//                                                   @PathVariable("category") String category,
//                                                   @PathVariable("date") String date) {
//        String pth = "/home/ec2-user/images/" +userIdentity +"/"+category+ "/" + filename + "/";
//        try {
//            FileSystemResource resource = new FileSystemResource(pth);
//            if (!resource.exists()) {
//                throw new NotFoundImageException();
//            }
//            HttpHeaders header = new HttpHeaders();
//            Path filePath = null;
//            filePath = Paths.get(pth);
//            header.add("Content-Type", Files.probeContentType(filePath));
//
//            return  new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
//        } catch (Exception e) {
//            throw new NotFoundImageException();
//        }
//    }

    @GetMapping("/user/clothesImage/{userIdentity}/{category}/{date}")
    public ResponseEntity<Resource> getImage(@PathVariable("userIdentity") String userIdentity,
                                             @PathVariable("category") String category,
                                             @PathVariable("date") String date) {

        List<Clothes> findClothes = clothesService.findClothesByUserIdentity(userIdentity);

        List<ClothesImageDto> selectClothes = findClothes.stream()
                .map(m -> new ClothesImageDto(m))
                .collect(Collectors.toList());

        ClothesImageDto clothesImage = null;

        for (ClothesImageDto clothesImageDto : selectClothes){
            if (clothesImageDto.getImagePath().contains(date) && clothesImageDto.getImagePath().contains(category)){
                clothesImage = clothesImageDto;
                log.info(clothesImage.getImagePath());
            }
        }

        try {
            FileSystemResource resource = new FileSystemResource(clothesImage.getImagePath());
            if (!resource.exists()) {
                throw new NotFoundImageException();
            }
            HttpHeaders header = new HttpHeaders();
            Path filePath = null;
            filePath = Paths.get(clothesImage.getImagePath());
            header.add("Content-Type", Files.probeContentType(filePath));

            return  new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundImageException();
        }
    }

    @Data
    @AllArgsConstructor
    static class ClothesImageDto {
        private String imagePath;

        public ClothesImageDto(Clothes clothes) {
            imagePath = clothes.getClothes_image().getImagePath();
        }

    }

//    @GetMapping("/user/clothesData/{userIdentity}/{category}/{date}")
//    public ClothesDataDto getdata(@PathVariable("userIdentity") String userIdentity,
//                                             @PathVariable("category") String category,
//                                             @PathVariable("date") String date) {
//
//        List<Clothes> findClothes = clothesService.findClothesByUserIdentity(userIdentity);
//
//        List<ClothesDataDto> selectClothes = findClothes.stream()
//                .map(m -> new ClothesDataDto(m))
//                .collect(Collectors.toList());
//
//        ClothesImageDto clothesImage = null;
//
//        for (ClothesDataDto clothesDataDto : selectClothes){
//            if (clothesDataDto.get().contains(date) && clothesDataDto.getImagePath().contains(category)){
//                clothesImage = clothesDataDto;
//                log.info(clothesImage.getImagePath());
//            }
//        }
//
//        return clothesImage;
//
//    }
    @Data
    @AllArgsConstructor
    static class ClothesDataDto {
        private Long id;
        private String name;
        private String url;
        private LocalDateTime date;
        private String brand;
        private String price;
        private Long size;


        public ClothesDataDto(Clothes clothes) {
        }
    }
}
