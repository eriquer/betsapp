package com.bets.mgr.service;

import com.bets.mgr.entity.Match;
import com.bets.mgr.entity.User;
import com.bets.mgr.entity.UserBet;
import com.bets.mgr.mapper.UserBetMapper;
import com.bets.mgr.mapper.UserBetDto;
import com.bets.mgr.repository.MatchRepository;
import com.bets.mgr.repository.UserBetRepository;
import com.bets.mgr.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserBetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBetRepository userBetRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserBetMapper userBetMapper;

    public List<UserBetDto> findAll() {
        return userBetMapper.listToDto(userBetRepository.findAll());
    }

    @Transactional
    public UserBetDto create(UserBetDto userBetdDto) {

        Optional<User> user = userRepository.findById(userBetdDto.getUser());
        user.orElseThrow( () ->  new Error("User not found"));

        Optional<Match> match = matchRepository.findByIdOptional( userBetdDto.getMatch() );
        match.orElseThrow( () -> new Error("MAtch not found") );

        UserBet newUserBet = userBetMapper.toEntity(userBetdDto);
        newUserBet.setCreated(LocalDateTime.now());

        return userBetMapper.toDto(userBetRepository.save(newUserBet));
    }
}
