package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Company;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Person;
import jakarta.servlet.http.HttpSession;

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
    public String login(ModelMap map) {
        return "redirect:accounts/login";
    }

    @GetMapping("/parceiros")
    public String parceiros() {
        return "pages/parceiros";
    }

    @GetMapping("/quem_somos")
    public String quemSomos() {
        return "pages/quem_somos";
    }

    @GetMapping("/registro_pessoa")
    public String registroPessoa(ModelMap model) {
        model.addAttribute("account", new Account());
        model.addAttribute("person", new Person());
        return "pages/registro_pessoa";
    }

    @GetMapping("/registro_empresa")
    public String registroEmpresa(ModelMap model) {
        model.addAttribute("account", new Account());
        model.addAttribute("company", new Company());
        return "pages/registro_empresa";
    }

    @GetMapping("/registro")
    public String registro() {
        return "pages/registro";
    }

    @GetMapping("/nav")
    public String nav() {
        return "fragments/admin_nav";
    }

    @GetMapping("/header")
    public String headerAdm() {
        return "fragments/header_adm";
    }

    @GetMapping("/clientnav")
    public String clientNav() {
        return "fragments/client_nav";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect: /";
    }
    
}
