package com.clonect.clothes.repository;

import com.clonect.clothes.domain.Feed;
import com.clonect.clothes.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedRepository {

    private final EntityManager em;

    public void save(Feed feed){
        em.persist(feed);
    }

    public Feed findOne(Long id){
        return em.find(Feed.class, id);
    }

    public List<Feed> findAII(){
        return em.createQuery("select i from Feed i", Feed.class)
                .getResultList();
    }

    public List<Feed> findByUserEmail(String userEmail) {
        return em.createQuery("select m from Member m where m.userEmail = :userEmail" , Feed.class)
                .setParameter("userEmail" , userEmail)
                .getResultList();
    }

    public List<Feed> findByUserId(Long userId) {
        return em.createQuery("select m from Feed m where m.userId = :userId" , Feed.class)
                .setParameter("userId" , userId)
                .getResultList();
    }

    public List<Feed> findByUserIdentity(String identity) {
        return em.createQuery("select m from Feed m where m.member.identity = :identity" , Feed.class)
                .setParameter("identity" , identity)
                .getResultList();
    }



}