package com.sjsu.cmpe.sstreet.webserver.controller;


import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @RequestMapping(path="/ping", method = RequestMethod.GET)
    public String ping(){
        return "pong";
    }
}
