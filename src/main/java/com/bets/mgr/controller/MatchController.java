package com.bets.mgr.controller;

import com.bets.mgr.entity.MatchEntity;
import com.bets.mgr.model.MatchResult;
import com.bets.mgr.repository.MatchRepository;
import com.bets.mgr.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MatchController {

    private static final Logger log = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchService matchService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("matches", matchService.findAllMatchs());
        return "index";
    }

    @RequestMapping(value = "/form")
    public String add(Model model) {

        model.addAttribute("match", new MatchEntity());

        return "form";
    }

    @RequestMapping(value = "/local/{id}")
    public String local(@PathVariable("id") Long id) {
        matchService.setWinner(id, MatchResult.LOCAL);
        return "redirect:/";
    }

    @RequestMapping(value = "/visitor/{id}")
    public String visitor(@PathVariable("id") Long id) {
        matchService.setWinner(id, MatchResult.VISITOR);
        return "redirect:/";
    }

    @RequestMapping(value = "/draw/{id}")
    public String draw(@PathVariable("id") Long id) {
        matchService.setWinner(id, MatchResult.DRAW);
        return "redirect:/";
    }

    @RequestMapping(value = "/matches", method = RequestMethod.POST)
    public String save(@Valid MatchEntity matchEntity, BindingResult result) {

        log.info("Match -> " + matchEntity);

        if (result.hasErrors()) {
            return "form";
        }

        matchService.saveMatch(matchEntity);
        return "redirect:/";
    }

}
