package com.referme.candidate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
public class CandidateHomeController {
    @GetMapping(value = "/CandidateHome")
    public ModelAndView getCandidateHome()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("CandidateHome");
       return modelAndView;
    }

    @GetMapping(value = "/")
    public ModelAndView home()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @GetMapping(value = "/landing")
    public ModelAndView landingPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("landing");
        return modelAndView;
    }
}
