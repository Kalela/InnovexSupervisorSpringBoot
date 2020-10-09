package com.kalela.InnovexSolutions.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DocsController {

    public String homePage() {
        return "Hello there";
    }
}
