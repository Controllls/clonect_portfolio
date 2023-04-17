package com.clonect.clothes.service;

import com.clonect.clothes.domain.Member;
import com.clonect.clothes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    public void validateDuplicateMember (Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재 하는 회원 입니다");
        }
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    public List<Member> findByIdentity(String identity){
        return memberRepository.findByIdentity(identity);
    }

    public List<Member> findByEmailPassword(String email , String password){
        return memberRepository.findByEmailPassword(email , password);
    }

    public List<Member> findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

}
