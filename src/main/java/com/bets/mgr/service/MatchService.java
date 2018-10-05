package com.bets.mgr.service;

import com.bets.mgr.entity.Match;
import com.bets.mgr.model.MatchResult;
import com.bets.mgr.repository.MatchRepository;
import lombok.AllArgsConstructor;
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

    public List<Match> findAllMatchs() {
        return matchRepository.findAll();
    }

    public void setWinner(Long id, MatchResult winner) {

        if (id == null) {
            return;
        }

        Match match = matchRepository.findById(id);
        if (match == null) {
            return;
        }

        match.setResult(winner);
        matchRepository.saveOrUpdate( match );
    }

    public void saveMatch(Match match) {

        matchRepository.saveOrUpdate( match );
    }

}
