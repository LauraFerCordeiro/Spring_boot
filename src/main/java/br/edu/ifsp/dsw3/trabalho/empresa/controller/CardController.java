package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CardDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Card;

@Controller
@RequestMapping("/cards")
public class CardController {

    @Autowired
    CardDAO cdao;

    @GetMapping("/cadastrar")
    public String cadastrar(Card card){
        return("/cards/cadastro");
    }

    @GetMapping("/listar")
    public String listar(ModelMap map){
        map.addAttribute("cards", cdao.findAll());
        return ("/cards/lista");
    }

    @PostMapping("/salvar")
    public String salvar(Card card){
        cdao.save(card);
        return("redirect:/cards/cadastrar");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("card", cdao.getReferenceById(id));
        return ("/cards/editar");
    }

    @PostMapping("/editar")
    public String alterar(Card card, RedirectAttributes attr){
        cdao.save(card);
        attr.addFlashAttribute("success", "Cartão editado com sucesso!");
        return("redirect:/cards/listar");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, ModelMap map){
        cdao.deleteById(id);
        map.addAttribute("success", "Cartão excluído com sucesso");
        return listar(map);
    }
    
}
