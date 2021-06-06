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
}
