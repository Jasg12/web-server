package com.sjsu.cmpe.sstreet.webserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardWebController {

    @GetMapping("/")
    public String getDashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }
}
