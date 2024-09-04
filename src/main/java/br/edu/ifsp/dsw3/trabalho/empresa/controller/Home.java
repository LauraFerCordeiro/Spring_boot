package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Home {

    @GetMapping(name = "/.")
    public String principal(){
        return "/home";
    }
}
