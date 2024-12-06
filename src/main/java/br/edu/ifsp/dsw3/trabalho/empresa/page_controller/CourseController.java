package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.AccountDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.LessonDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PayCourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Lesson;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.PayCourse;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Person;
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

    @Autowired
    AccountDAO aDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Course course){
        return("pages/courses/cadastrar");
    }

    @GetMapping("/course/{id}")
    public String curso(@PathVariable("id") Long id, ModelMap map) {
        Course course = cDao.findById(id).orElse(null);
        if(course!= null){
            map.addAttribute("course", course);
            map.addAttribute("first_lesson_link", course.getFirstLesson().getLessonLink());
            return "pages/courses/course";
        }
        return "pages/courses/course";
    }

    @GetMapping("/matricular/{id}")
    public String matricular(@PathVariable("id") Long id, ModelMap map) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = aDao.findByEmail(username);

        Course course = cDao.findById(id).orElse(null);

        Person p = (Person) account.getClient();

        pDao.save(new PayCourse(LocalDate.now(), course, p));

        return "redirect:/people/home";
    }
    

    @GetMapping("/lista")
    public String listar(ModelMap map){
        map.addAttribute("courses", cDao.findAll());
        return ("pages/courses/lista");
    }

    @GetMapping("/lessons/{id}")
    public String aulas(@PathVariable("id") Long id, ModelMap map){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = aDao.findByEmail(username);
        
        Course course = cDao.findById(id).orElse(null);
        
        Person p = (Person) account.getClient();
        
        if (p.getCourses().contains(course)) {
            List<Lesson> lessons = cDao.findById(id).orElse(null).getLessons();
        
            lessons.removeIf(lesson -> lesson.getRelease().isAfter(LocalDate.now()));
        
            map.addAttribute("lessons", lessons);
        
            return "pages/people/lessons";
        } else {
            return "redirect:/people/home";
        }
    }

    @GetMapping("/lesson/{id}")
    public String aula(@PathVariable("id") Long id, ModelMap map){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = aDao.findByEmail(username);

        Course course = lDao.findById(id).orElse(null).getCourse();

        Person p = (Person) account.getClient();

        if(p.getCourses().contains(course)){
            if(lDao.findById(id).orElse(null).getRelease().isAfter(LocalDate.now())){
                return "redirect:/people/home";
            }

            map.addAttribute("lesson", lDao.findById(id).orElse(null));
            return "pages/people/lesson";
        }else{
            return "redirect:/people/home";
        }
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
