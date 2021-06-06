package com.gyuyeon.springbook.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class paintController {

    @GetMapping("/paint")
    public String paint() {
        return "/paint/index";
    }

    @ResponseBody
    @GetMapping("/paint1")
    public String paint1() {
        return "paint";
    }

    @GetMapping("/paint2")
    public String paint2() {
        return "/paint/index23131";
    }
}
