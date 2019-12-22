package com.noman.insulinpumpsimulation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> welcomeMessage() {
        return new ResponseEntity("Welcome to the Simulation Service",HttpStatus.OK);
    }
}
