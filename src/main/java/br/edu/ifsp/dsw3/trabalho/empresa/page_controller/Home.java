package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.AccountDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Company;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Person;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Role;
import jakarta.servlet.http.HttpSession;


@Controller
public class Home {

    @Autowired
    AccountDAO aDao;

    @GetMapping(name = "/.")
    public String principal(){
        return "/home";
    }

    @GetMapping("/contato")
    public String contato() {
        return "pages/contato";
    }

    @GetMapping("/login")
    public String login(Account account) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account a = aDao.findByEmail(username);

        if(a != null){
            if(a.getRole().equals(Role.ADMIN)){
                return "redirect:/dashbord/admin";
            }
            else if(a.getRole().equals(Role.PERSON)){
                return "redirect:/people/home";
            }
        }


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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect: /";
    }

        @ModelAttribute("username")
    public String getUsername(){
        String nome = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails){
            nome = userDetails.getUsername();
        }
        return nome;
    }
    
}
