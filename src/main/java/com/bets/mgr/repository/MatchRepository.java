package com.bets.mgr.repository;

import com.bets.mgr.entity.Match;
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
    public List<Match> findAll() {

        return em.createQuery("from Match").getResultList();
    }

    public Match findById(Long id) {

        return em.find(Match.class, id);
    }

    @Transactional
    public void saveOrUpdate(Match match) {
        if (match.getId() != null && match.getId() > 0) {
            em.merge( match );
        } else {
            em.persist( match );
        }
    }
}
