package com.freemark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class HelloFreeMarkerController {

    @RequestMapping("/helloFreeMarker")
    public String helloFreeMarker(Model model) {
        model.addAttribute("name","ITDragon博客");
        return "FreeMarker";
    }

}