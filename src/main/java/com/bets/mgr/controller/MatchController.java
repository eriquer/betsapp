package com.bets.mgr.controller;

import com.bets.mgr.entity.Match;
import com.bets.mgr.model.MatchResult;
import com.bets.mgr.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MatchController {

    private static final Logger log = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchService matchService;

    @Secured( {"ROLE_USER"} )
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("matches", matchService.findAllMatchs());
        return "index";
    }

    @Secured( {"ROLE_ADMIN"} )
    @RequestMapping(value = "/form")
    public String add(Model model) {

        model.addAttribute("match", new Match());
        return "form";
    }

    @Secured( {"ROLE_ADMIN"} )
    @RequestMapping(value = "/local/{id}")
    public String local(@PathVariable("id") Long id) {
        matchService.setWinner(id, MatchResult.LOCAL);
        return "redirect:/";
    }

    @Secured( {"ROLE_ADMIN"} )
    @RequestMapping(value = "/visitor/{id}")
    public String visitor(@PathVariable("id") Long id) {
        matchService.setWinner(id, MatchResult.VISITOR);
        return "redirect:/";
    }

    @Secured( {"ROLE_ADMIN"} )
    @RequestMapping(value = "/draw/{id}")
    public String draw(@PathVariable("id") Long id) {
        matchService.setWinner(id, MatchResult.DRAW);
        return "redirect:/";
    }

    @Secured( {"ROLE_ADMIN"} )
    @RequestMapping(value = "/matches", method = RequestMethod.POST)
    public String save(@Valid Match match, BindingResult result) {

        log.info("Match -> " + match );

        if (result.hasErrors()) {
            return "form";
        }

        matchService.saveMatch( match );
        return "redirect:/";
    }

}
