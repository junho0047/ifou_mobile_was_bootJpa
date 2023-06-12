package com.nifou.m.ifou_mobile_was.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value="/")
    public String index() {
        return "index";
    }
    @GetMapping("/testPage")
    public String test() {
        return "testPage";
    }
}
