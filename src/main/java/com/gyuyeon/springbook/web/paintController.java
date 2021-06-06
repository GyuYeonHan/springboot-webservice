package com.gyuyeon.springbook.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class paintController {

    @GetMapping("/paint")
    public String paint() {
        return "/paint/index";
    }
}
