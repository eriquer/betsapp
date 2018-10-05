package com.bets.mgr.repository;

import com.bets.mgr.entity.UserBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBetRepository extends JpaRepository<UserBet, Long> {


}
