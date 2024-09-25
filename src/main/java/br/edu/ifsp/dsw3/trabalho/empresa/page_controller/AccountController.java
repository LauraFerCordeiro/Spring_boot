package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import jakarta.servlet.http.HttpSession;

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

    @PostMapping("/salvar")
    public String salvar(Account account) {
        adao.save(account);
        return ("redirect:/accounts/cadastrar");
    }

    @PostMapping("/person/salvar")
    public String salvar(@ModelAttribute Person person, @RequestParam("email") String email,
            @RequestParam("password") String password) {

        // Criar objeto Account
        Integer view = 1;
        Boolean admin = false;
        Account a = new Account(email, password, person.getName(), view, admin, person, null);
        person.setAccount(a);

        // Salvar no banco de dados
        pDao.save(person);
        adao.save(a);

        return "redirect:/login";
    }

    @PostMapping("/company/salvar")
    public String salvar(@ModelAttribute Company company, @RequestParam("email") String email,
            @RequestParam("password") String password) {

        // Criar objeto Account
        Integer view = 2;
        Boolean admin = false;
        Account a = new Account(email, password, company.getName(), view, admin, company, null);
        company.setAccount(a);

        // Salvar no banco de dados
        cDao.save(company);
        adao.save(a);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(ModelMap map) {
        return "pages/login";
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

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, ModelMap map, HttpSession session) {
        Account a = adao.findByEmail(email).filter(account -> account.getPassword().equals(password)).orElse(null);
        if (a != null) {

            // Adiciona o usuário na sessão
            session.setAttribute("loggedInUser", a);

            // Log para verificar se a sessão está configurada corretamente
            System.out.println("Usuário logado: " + session.getAttribute("loggedInUser").toString());
            switch (a.getView()) {
                case 1:
                    return "redirect:/people/home";
                case 2:
                    return "redirect:/companies/home";
                case 3:
                    return "redirect:/workers/home";
                case 4:
                    return "redirect:/admin";
                default:
                    return "redirect:/";
            }
        } else {
            map.addAttribute("error", "Usuário ou senha inválidos");
            return login(map);
        }
    }

}
