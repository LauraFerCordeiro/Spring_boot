package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PayCourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PersonDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Person;

@Controller
@RequestMapping("/people")
public class PersonController {
    @Autowired
    PersonDAO pDao;

    @Autowired
    PayCourseDAO pCDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Person person){
        return("pages/people/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map){
        map.addAttribute("people", pDao.findAll());
        return ("pages/people/lista");
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Person person){
        pDao.save(person);
        return("redirect:/people/lista");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("person", pDao.getReferenceById(id));
        return ("pages/people/editar");
    }

    @PostMapping("/editar/{id}")
    public String alterar(@PathVariable("id") Long id,  @ModelAttribute Person person, RedirectAttributes attr){
        person.setId(id);
        pDao.save(person);
        attr.addFlashAttribute("success", "Pessoa editada com sucesso!");
        return("redirect:/people/lista");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, ModelMap map){
        if(pCDao.findPaysByPersonId(id).isEmpty()){
            pDao.deleteById(id);
        }else{
            pCDao.deletePaysByPersonId(id);
            pDao.deleteById(id);
        }

        map.addAttribute("success", "Pessoa excluída com sucesso!");
        return listar(map);
    }

    @GetMapping("/home")
    public String peopleHome(ModelMap map) {
        
        return "pages/people/home";
    }

    @GetMapping("/meuscursos")
    public String peopleMeusCursos() {
        return "pages/people/meuscursos";
    }

    @GetMapping("/todoscursos")
    public String peopleTodosCursos() {
        return "pages/people/todoscursos";
    }
}
