package com.bets.mgr.mapper;

import com.bets.mgr.model.MatchResult;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserBetDto implements Serializable {

    private static final long serialVersionUID = 2603253896094783116L;

    private Long id;

    @NotNull
    private Long user;

    @NotNull
    private Long match;

    @NotNull
    private MatchResult result;

    @NotNull
    @Min(value = 1)
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getMatch() {
        return match;
    }

    public void setMatch(Long match) {
        this.match = match;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}

