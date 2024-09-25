package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PayCourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Lesson;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.PayCourse;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    PayCourseDAO pDao;

    @Autowired
    CourseDAO cDao;

    @Autowired
    LessonDAO lDao;

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
    public String salvar(@ModelAttribute Course course){
        cDao.save(course);
        return("redirect:/courses/lista");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("course", cDao.getReferenceById(id));
        return ("pages/courses/editar");
    }

    @PostMapping("/editar/{id}")
    public String alterar(@PathVariable("id") Long id, @ModelAttribute Course course, RedirectAttributes attr){
        cDao.save(course);
        attr.addFlashAttribute("success", "Curso editado com sucesso!");
        return("redirect:/courses/lista");
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

    @Transactional
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap map) {
        Optional<Course> optionalCourse = cDao.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            // Recarregar a entidade do banco de dados
            course = cDao.findById(id).orElse(null);
            if (course == null) {
                map.addAttribute("error", "Curso não encontrado");
                return listar(map);
            }

            try {
                // Deletar as lições associadas ao curso
                if (!course.getLessons().isEmpty()) {
                    for (Lesson lesson : course.getLessons()) {
                        Optional<Lesson> optionalLesson = lDao.findById(lesson.getId());
                        if (optionalLesson.isPresent()) {
                            lDao.delete(optionalLesson.get());
                        }
                    }
                }

                // Deletar os pagamentos associados ao curso
                List<PayCourse> pays = pDao.findPaysByCourseId(id);
                if (!pays.isEmpty()) {
                    for (PayCourse pay : pays) {
                        Optional<PayCourse> optionalPay = pDao.findById(pay.getId());
                        if (optionalPay.isPresent()) {
                            pDao.delete(optionalPay.get());
                        }
                    }
                }

                // Deletar o curso
                cDao.delete(course);

                map.addAttribute("success", "Curso excluído com sucesso");
            } catch (ObjectOptimisticLockingFailureException e) {
                map.addAttribute("error", "O curso foi atualizado ou deletado por outra transação. Tente novamente.");
                return listar(map);
            }
        } else {
            map.addAttribute("error", "Curso não encontrado");
        }
        return listar(map);
    }
}
