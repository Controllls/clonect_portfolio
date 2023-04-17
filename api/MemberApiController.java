package com.clonect.clothes.api;

import com.clonect.clothes.domain.Member;
import com.clonect.clothes.service.AppleService;
import com.clonect.clothes.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;
    private final AppleService appleService;

    public String encodeBcrypt(String planeText, int strength) {
        return new BCryptPasswordEncoder(strength).encode(planeText);
    }



     /*
    회원가입

    input data: json
    {
    birth" :
    "email" :
    "gender" :
    "identity" :
    "name" :
    "password" :
    "phone" :
    }
     */
    @PostMapping("/api/save/members")
    public CreateMemberResponse saveMemberSNS(@RequestBody CreateMemberRequest request) {
        String uuid = UUID.randomUUID().toString().replace("-", "");;

        Member member = new Member();
        member.setName(request.getName());
        if (request.getIdentity() == null){
            member.setIdentity(uuid);

            String path = "/home/ec2-user/images/" + uuid + "/";
            File Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    log.info("회원 폴더 생성 실패");
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + uuid + "/feed/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }

            //카테고리별 파일 왕창 생성
            path = "/home/ec2-user/images/" + uuid + "/top/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + uuid + "/bottom/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + uuid + "/outer/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + uuid + "/shoes/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + uuid + "/bag/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + uuid + "/accessory/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }

        }else{
            String new_identity = appleService.userIdFromApple(request.getIdentity());

            member.setIdentity(new_identity);

            String path = "/home/ec2-user/images/" + new_identity + "/";
            File Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    log.info("회원 폴더 생성 실패");
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + new_identity + "/feed/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }

            //카테고리별 파일 왕창 생성
            path = "/home/ec2-user/images/" + new_identity + "/top/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + new_identity + "/bottom/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + new_identity + "/outer/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + new_identity + "/shoes/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + new_identity + "/bag/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            path = "/home/ec2-user/images/" + new_identity + "/accessory/";
            Folder = new File(path);
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }

        }

        member.setEmail(request.getEmail());
        member.setGender(request.getGender());
        member.setPhone(request.getPhone());
        //member.setPassword(request.getPassword());
        member.setPassword(encodeBcrypt(request.getPassword() , 10));

        member.setBirth(request.getBirth());

        //member 전용 폴더 생성

        Long id = memberService.join(member);
        if (request.getIdentity() == null){
            return new CreateMemberResponse(uuid);
        }else{
            return new CreateMemberResponse(request.getIdentity());
        }

    }

    @Data
    @AllArgsConstructor
    static class CreateMemberRequest {
        String birth;
        String email;
        String gender;
        String identity;
        String name;
        String password;
        String phone;

    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {

        private String identity;

    }
}
