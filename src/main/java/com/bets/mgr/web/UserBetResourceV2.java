package com.bets.mgr.web;

import com.bets.mgr.mapper.UserBetDto;
import com.bets.mgr.service.UserBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class UserBetResourceV2 {

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

    // @Valid, aplica todas las validaciones definidas en Dto
    @PostMapping(value = "users/{user}/bets")
    public ResponseEntity<UserBetDto> create(@PathVariable("user") Long user,
            @Valid @RequestBody UserBetDto userBetDto) {

        UserBetDto otherUserBetDto = userBetService.create( userBetDto );
        return new ResponseEntity<>(otherUserBetDto, HttpStatus.CREATED) ;
    }
}
