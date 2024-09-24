package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Department;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartmentDAO;

@Controller
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    WorkerDAO wDao;

    @Autowired
    DepartmentDAO ddao;

    @GetMapping("/cadastrar")
    public String cadastrar(Worker worker){
        return("/pages/workers/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map){
        map.addAttribute("workers", wDao.findAll());
        return ("pages/workers/lista");
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Worker worker){
        wDao.save(worker);
        return("redirect:/workers/lista");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("worker", wDao.getReferenceById(id));
        return ("pages/workers/editar");
    }

    @PostMapping("/editar/{id}")
    public String alterar(Worker worker, RedirectAttributes attr){
        wDao.save(worker);
        attr.addFlashAttribute("success", "Funcionario editado com sucesso!");
        return("redirect:/workers/lista");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, ModelMap map){
        wDao.deleteById(id);
        map.addAttribute("success", "Trabalhador excluído com sucesso!");
        return listar(map);
    }

    @ModelAttribute("departments")
    public List<Department> getDepartaments (){
        return ddao.findAll();
    }
}
