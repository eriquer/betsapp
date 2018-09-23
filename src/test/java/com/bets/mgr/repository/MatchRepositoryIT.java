package com.bets.mgr.repository;

import com.bets.mgr.entity.Match;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MatchRepositoryIT {

    @Autowired
    private MatchRepository repository;

    @Test
    public void createTest() {

        Match match = new Match();
        match.setLocal("LOCAL");
        match.setVisitor("VISITOR");
        match.setOpen(LocalDateTime.now());
        match.setClose(LocalDateTime.now());

        repository.saveOrUpdate(match);

        List<Match> matches = repository.findAll();

        assertThat(matches).isNotEmpty();
    }
}
