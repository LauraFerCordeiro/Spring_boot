package br.edu.ifsp.dsw3.trabalho.empresa.controller;

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

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartmentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.VulnerabilityDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Department;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
   @Autowired
   DepartmentDAO dDao;

   @Autowired
   WorkerDAO wDao;

   @Autowired
   VulnerabilityDAO vDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Department department){
        return("/pages/departments/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map){
        map.addAttribute("departments", dDao.findAll());
        return ("/pages/departments/lista");
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Department department){
        dDao.save(department);
        return("redirect:/departments/lista");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("department", dDao.getReferenceById(id));
        return ("pages/departments/editar");
    }

    @PostMapping("/editar/{id}")
    public String alterar(Department department, RedirectAttributes attr){
        dDao.save(department);
        attr.addFlashAttribute("success", "Departamento editado com sucesso!");
        return("redirect:/departments/lista");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, ModelMap map){
        List<Worker> workers = wDao.findWorkersByDepartmentId(id);

        if(workers.isEmpty()){
            dDao.deleteById(id);
        }else{
            for(Worker worker : workers){
                if(vDao.findVulnerabilitiesByWorkerId(worker.getId()).isEmpty()){
                    wDao.deleteById(id);
                }else{
                    vDao.deleteVulnerabilitiesByWorkerId(worker.getId());
                    wDao.deleteById(worker.getId());
                }
            }

            dDao.deleteById(id);
        }

        map.addAttribute("success", "Departamento excluído com sucesso");
        return listar(map);
    }

}
