package com.clonect.clothes.repository;

import com.clonect.clothes.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m" , Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name" , Member.class)
                .setParameter("name" , name)
                .getResultList();
    }

    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email" , Member.class)
                .setParameter("email" , email)
                .getResultList();
    }



    public List<Member> findByIdentity(String identity) {
        return em.createQuery("select m from Member m where m.identity = :identity" , Member.class)
                .setParameter("identity" , identity)
                .getResultList();
    }

    public List<Member> findByEmailPassword(String email, String password){
        return em.createQuery("select m from Member m where m.email = :email AND m.password = :password" , Member.class)
                .setParameter("email" , email)
                .setParameter("password" , password)
                .getResultList();
    }

}