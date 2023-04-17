package com.clonect.clothes.repository;

import com.clonect.clothes.domain.Feed;
import com.clonect.clothes.domain.Member;
import com.clonect.clothes.domain.clothes.Clothes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClothesRepository {

    private final EntityManager em;


    public void save(Clothes clothes){
        if(clothes.getId() == null){
            em.persist(clothes);
        }else{
            em.merge(clothes);
        }
    }

    public Clothes findOne(Long id){
        return em.find(Clothes.class, id);
    }

    public List<Clothes> findByUserIdentity(String identity) {
        return em.createQuery("select m from Clothes m where m.member.identity = :identity" , Clothes.class)
                .setParameter("identity" , identity)
                .getResultList();
    }

    public List<Clothes> findByFeedId(Long feedId) {
        return em.createQuery("select m from Clothes m where m.feed.id = :feed_id" , Clothes.class)
                .setParameter("feed_id" , feedId)
                .getResultList();
    }


}
