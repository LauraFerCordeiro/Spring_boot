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

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.LessonDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Lesson;


@Controller
@RequestMapping("/lessons")
public class LessonController {
    
    @Autowired
    LessonDAO lDao;

    @Autowired
    CourseDAO cDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Lesson lesson){
        return("pages/lessons/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map){
        map.addAttribute("lessons", lDao.findAll());
        return ("pages/lessons/lista");
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Lesson lesson){
        lDao.save(lesson);
        return("redirect:/lessons/lista");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("lesson", lDao.getReferenceById(id));
        return ("pages/lessons/editar");
    }

    @PostMapping("/editar/{id}")
    public String alterar(@PathVariable("id") Long id, @ModelAttribute Lesson lesson, RedirectAttributes attr){
        lDao.save(lesson);
        attr.addFlashAttribute("success", "Aula editada com sucesso!");
        return("redirect:/lessons/lista");
    }

    /* @GetMapping("/excluir/{id}")
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
    */

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap map){
        lDao.deleteById(id);
        map.addAttribute("success", "Aula excluída com sucesso!");
        return listar(map);
    }

    @ModelAttribute("courses")
    public List<Course> getDepartaments (){
        return cDao.findAll();
    }
}
