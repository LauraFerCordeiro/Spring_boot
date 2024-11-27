package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.AccountDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CompanyDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PersonDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Company;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Person;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Role;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountDAO adao;

    @Autowired
    PersonDAO pDao;

    @Autowired
    CompanyDAO cDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Account Account) {
        return ("pages/accounts/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map) {
        map.addAttribute("accounts", adao.findAll());
        return ("pages/accounts/lista");
    }

    @PostMapping("/person/salvar")
    public String salvar(
            @ModelAttribute Account account,
            @RequestParam("name") String name,
            @RequestParam("telephone") String telephone,
            @RequestParam("cpf") String cpf,
            @RequestParam("birthDate") String birthDate) {

        Person person = new Person();
        person.setName(name);
        person.setTelephone(telephone);
        person.setCpf(cpf);
        person.setBirthDate(LocalDate.parse(birthDate));
        person.setAccount(account);

        account.setRole(Role.PERSON);

        pDao.save(person);
        adao.save(account);

        return "redirect:/login";
    }

    @PostMapping("/company/salvar")
    public String salvar(
            @ModelAttribute Account account,
            @RequestParam("name") String name,
            @RequestParam("telephone") String telephone,
            @RequestParam("cnpj") String cnpj) {
    
        account.setRole(Role.COMPANY);
    
        Company company = new Company();
        company.setName(name);
        company.setTelephone(telephone);
        company.setCnpj(cnpj);
        company.setAccount(account);
    
        adao.save(account); // Salva o Account
        cDao.save(company); // Salva a Company
    
        return "redirect:/login";
    }
    

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id") Long id) {
        map.addAttribute("account", adao.getReferenceById(id));
        return ("/account/editar");
    }

    @PostMapping("/editar")
    public String alterar(Account account, RedirectAttributes attr) {
        adao.save(account);
        attr.addFlashAttribute("success", "Conta editada com sucesso!");
        return ("redirect:/accounts/listar");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap map) {
        adao.deleteById(id);
        map.addAttribute("success", "Conta excluída com sucesso");
        return listar(map);
    }

    @ModelAttribute("tipos")
    public Role[] getRoles() {
        return Role.values();
    }

    @ModelAttribute("username")
    public String getUsername() {
        String nome = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            nome = userDetails.getUsername();
        }
        return nome;
    }

    // @ModelAttribute("name")
    // public String getName() {
    //     Account account = adao.findByEmail(getUsername());
    //     return account.getName();
    // }
}
