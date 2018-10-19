package com.bets.mgr.web;

import com.bets.mgr.entity.Match;
import com.bets.mgr.mapper.UserBetDto;
import com.bets.mgr.repository.MatchRepository;
import com.bets.mgr.service.UserBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchResource {

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping(value = "matches", produces="application/json")
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

}
