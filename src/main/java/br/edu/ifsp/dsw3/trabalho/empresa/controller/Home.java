package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Home {

    @GetMapping(name = "/.")
    public String principal(){
        return "/home";
    }

    @GetMapping("/contato")
    public String contato() {
        return "pages/contato";
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

    @GetMapping("/parceiros")
    public String parceiros() {
        return "pages/parceiros";
    }

    @GetMapping("/quem_somos")
    public String quemSomos() {
        return "pages/quem_somos";
    }

    @GetMapping("/registro_empresa")
    public String registroEmpresa() {
        return "pages/registro_empresa";
    }

    @GetMapping("/registro_pessoa")
    public String registroPessoa() {
        return "pages/registro_pessoa";
    }

    @GetMapping("/registro")
    public String registro() {
        return "pages/registro";
    }
}
