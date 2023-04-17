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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginApi {
    private final MemberService memberService;
    private final AppleService appleService;



    public boolean matchesBcrypt(String planeText, String hashValue, int strength) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
        return passwordEncoder.matches(planeText, hashValue);
    }

    public String encodeBcrypt(String planeText, int strength) {
        return new BCryptPasswordEncoder(strength).encode(planeText);
    }


    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        if (request.getIdentity() != null) {
            try {
                String new_identity = appleService.userIdFromApple(request.getIdentity());
                List<Member> valids = memberService.findByIdentity(new_identity);
                Member valid = valids.get(0);
                return new LoginResponse(valid.getIdentity());
            } catch (Exception e) {
                return null;
            }
        } else {

            try {

                List<Member> byEmail = memberService.findByEmail(request.getEmail());
                Member be = byEmail.get(0);
                if (matchesBcrypt( request.getPassword(),be.getPassword(), 10)) {
//                    List<Member> valids = memberService.findByEmailPassword(request.getEmail(), encodeBcrypt(request.getPassword() , 10));
//                    Member valid = valids.get(0);

                    return new LoginResponse(be.getIdentity());
                }else{
                    return null;
                }



            } catch (Exception e) {
                return null;
            }
        }
    }



    @Data
    @AllArgsConstructor
    static class LoginResponse {

        private String identity;

    }

    @Data
    @AllArgsConstructor
    static class LoginRequest {
        private String identity;
        private String email;
        private String password;

    }
}
