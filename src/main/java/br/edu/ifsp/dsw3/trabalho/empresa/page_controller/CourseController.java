package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PayCourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    PayCourseDAO pDao;

    @Autowired
    CourseDAO cDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Course course){
        return("pages/courses/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map){
        map.addAttribute("courses", cDao.findAll());
        return ("pages/courses/lista");
    }

    @PostMapping("/salvar")
    public String salvar(Course course){
        cDao.save(course);
        return("redirect:/courses/cadastrar");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("course", cDao.getReferenceById(id));
        return ("/courses/editar");
    }

    @PostMapping("/editar")
    public String alterar(Course course, RedirectAttributes attr){
        cDao.save(course);
        attr.addFlashAttribute("success", "Curso editado com sucesso!");
        return("redirect:/courses/lista");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, ModelMap map){
        if(pDao.findPaysByCourseId(id).isEmpty()){
            cDao.deleteById(id);
        }else{
            pDao.deletePaysByCourseId(id);
            cDao.deleteById(id);
        }

        map.addAttribute("success", "Curso excluído com sucesso");
        return listar(map);
    }
}
