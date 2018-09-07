package com.bets.mgr.repository;

import com.bets.mgr.entity.MatchEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MatchRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<MatchEntity> findAll() {
        return em.createQuery("from Match").getResultList();
    }

    public MatchEntity findById(Long id) {
        return em.find(MatchEntity.class, id);
    }

    @Transactional
    public void saveOrUpdate(MatchEntity matchEntity) {
        if (matchEntity.getId() != null && matchEntity.getId() > 0) {
            em.merge(matchEntity);
        } else {
            em.persist(matchEntity);
        }
    }
}
