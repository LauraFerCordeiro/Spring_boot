package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.AccountDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountDAO adao;

    @GetMapping("/cadastrar")
    public String cadastrar(Account Account){
        return("pages/accounts/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map){
        map.addAttribute("accounts", adao.findAll());
        return ("pages/accounts/lista");
    }

    @PostMapping("/salvar")
    public String salvar(Account account){
        adao.save(account);
        return("redirect:/accounts/cadastrar");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("account", adao.getReferenceById(id));
        return ("/account/editar");
    }

    @PostMapping("/editar")
    public String alterar(Account account, RedirectAttributes attr){
        adao.save(account);
        attr.addFlashAttribute("success", "Conta editada com sucesso!");
        return("redirect:/accounts/listar");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, ModelMap map){
        adao.deleteById(id);
        map.addAttribute("success", "Conta excluída com sucesso");
        return listar(map);
    }
    
}
