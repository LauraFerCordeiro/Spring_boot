package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;

@Controller
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    WorkerDAO wDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Worker worker){
        return("/workers/cadastro");
    }

    @GetMapping("/listar")
    public String listar(ModelMap map){
        map.addAttribute("workers", wDao.findAll());
        return ("/workers/lista");
    }

    @PostMapping("/salvar")
    public String salvar(Worker worker){
        wDao.save(worker);
        return("redirect:/workers/cadastrar");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("woker", wDao.getReferenceById(id));
        return ("/workers/editar");
    }

    @PostMapping("/editar")
    public String alterar(Worker worker, RedirectAttributes attr){
        wDao.save(worker);
        attr.addFlashAttribute("success", "Funcionario editado com sucesso!");
        return("redirect:/workers/listar");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, ModelMap map){
        wDao.deleteById(id);
        map.addAttribute("success", "Trabalhador excluído com sucesso!");
        return listar(map);
    }
}
