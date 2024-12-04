package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.AccountDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PersonDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Person;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Role;
import jakarta.servlet.http.HttpSession;


@Controller
public class Home {

    @Autowired
    AccountDAO aDao;

    @Autowired
    PersonDAO pDao;
    
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
                return "redirect:/dashboard/admin";
            }
            else if(a.getRole().equals(Role.PERSON)){
                return "redirect:/people/home";
            }
        }


        return "pages/login";
    }

    @GetMapping("/dashboard/admin")
    public String dashboardAdmin() {
        return "pages/admin";
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
        model.addAttribute("erro", "erro");
        model.addAttribute("account", new Account());
        model.addAttribute("person", new Person());
        return "pages/registro_pessoa";
    }

    @PostMapping("accounts/person/salvar")
    public String salvar(
            @ModelAttribute Account account,
            @RequestParam("name") String name,
            @RequestParam("telephone") String telephone,
            @RequestParam("cpf") String cpf,
            @RequestParam("birthDate") String birthDate,
            @RequestParam("rsenha") String rsenha) {
                
        Person person = new Person();
        person.setName(name);
        person.setTelephone(telephone);
        person.setCpf(cpf);
        person.setBirthDate(LocalDate.parse(birthDate));
        person.setAccount(account);

        account.setRole(Role.PERSON);

        pDao.save(person);
        aDao.save(account);

        return "redirect:/login";
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
