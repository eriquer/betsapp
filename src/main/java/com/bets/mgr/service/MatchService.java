package com.bets.mgr.service;

import com.bets.mgr.entity.MatchEntity;
import com.bets.mgr.model.MatchResult;
import com.bets.mgr.repository.MatchRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<MatchEntity> findAllMatchs() {
        return matchRepository.findAll();
    }

    public void setWinner(Long id, MatchResult winner) {
        if (id == null) {
            return;
        }

        MatchEntity matchEntity = matchRepository.findById(id);
        if (matchEntity == null) {
            return;
        }

        matchEntity.setResult(winner);
        matchRepository.saveOrUpdate(matchEntity);
    }

    public void saveMatch(MatchEntity matchEntity) {
        matchRepository.saveOrUpdate(matchEntity);
    }

}
