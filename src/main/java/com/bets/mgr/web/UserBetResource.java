package com.bets.mgr.web;

import com.bets.mgr.mapper.UserBetDto;
import com.bets.mgr.service.UserBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserBetResource {

    @Autowired
    UserBetService userBetService;


    /* Body example
    {
        "user": "1"
        "match" : "1"
        "amount" : "80.3"
        "result" : "LOCAL"
    }
    */
    @PostMapping(value = "user-bets")
    public UserBetDto create(@RequestBody UserBetDto userBetDto) {
        return userBetService.create( userBetDto );
    }

    @GetMapping(value = "user-bets", produces="application/json")
    public List<UserBetDto> findAll() {
        return userBetService.findAll();
}


}
