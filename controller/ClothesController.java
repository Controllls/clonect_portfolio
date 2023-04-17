//package com.clonect.clothes.controller;
//
//import com.clonect.clothes.domain.clothes.Clothes;
//import com.clonect.clothes.domain.clothes.Clothes_Image;
//import com.clonect.clothes.service.ClothesService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//public class ClothesController {
//    private final ClothesService clothesService;
//
//    @GetMapping("/clothes/new")
//    public String createForm(Model model) {
//        model.addAttribute("form", new ClothesForm());
//        return "clothes/createClothesForm";
//    }
//
//    @PostMapping("/clothes/new")
//    public String create(ClothesForm form , MultipartFile multipㄹartFile) {
//
//        Clothes clothes = new Clothes();
//        clothes.setName(form.getName());
//        clothes.setSize(form.getSize());
//
//        Clothes_Image clothes_image = new Clothes_Image();
//        clothes_image.setFile_name(form.getFile_name());
//        clothes_image.setImagePath(form.getImagePath());
//
//        clothes.setClothes_image(clothes_image);
//
//
//
//        String path = "/Users/choejong-geun/Desktop/images/"+form.getEmail()+"/clothes/"; // path바꿔야해
//        File Folder = new File(path);
//
//        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
//        if (!Folder.exists()) {
//            try{
//                Folder.mkdir(); //폴더 생성합니다.
//            }
//            catch(Exception e){
//                e.getStackTrace();
//            }
//        }
//
//        Path imagePath = Paths.get(path + form.getFile_name());
//        try {
//            Files.write(imagePath, multipartFile.getBytes());
//        } catch (Exception e) {
//
//        }
//
//        clothesService.join(clothes);
//        return "redirect:/";
//    }
//}
