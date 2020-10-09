package com.kalela.InnovexSolutions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DocsController {

    @GetMapping
    public String homePage() {
        return "Hello there";
    }
}
