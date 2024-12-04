package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PayCourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PersonDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Lesson;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Person;

@Controller
@RequestMapping("/people")
public class PersonController {
    @Autowired
    PersonDAO pDao;

    @Autowired
    PayCourseDAO pCDao;

    @Autowired
    CourseDAO cDao;

    @Autowired
    AccountDAO aDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Person person) {

        return ("pages/people/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map) {

        map.addAttribute("people", pDao.findAll());
        return ("pages/people/lista");
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Person person) {
        pDao.save(person);
        return ("redirect:/people/lista");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id") Long id) {
        map.addAttribute("person", pDao.getReferenceById(id));
        return ("pages/people/editar");
    }

    @PostMapping("/editar/{id}")
    public String alterar(@PathVariable("id") Long id, @ModelAttribute Person person, RedirectAttributes attr) {
        person.setId(id);
        pDao.save(person);
        attr.addFlashAttribute("success", "Pessoa editada com sucesso!");
        return ("redirect:/people/lista");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap map) {
        if (pCDao.findPaysByPersonId(id).isEmpty()) {
            pDao.deleteById(id);
        } else {
            pCDao.deletePaysByPersonId(id);
            pDao.deleteById(id);
        }

        map.addAttribute("success", "Pessoa excluída com sucesso!");
        return listar(map);
    }

    @GetMapping("/home")
    public String home(ModelMap map) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = aDao.findByEmail(username);

        map.addAttribute("name", account.getName());
        if (account != null && account.getClient() instanceof Person person) {
            List<Course> courses = person.getCourses();
            for (Course course : courses) {
                Boolean flag = false;
                List<Lesson> lessons = course.getLessons();
                for (Lesson lesson : lessons) {
                    if(lesson.getLessonNumber().equals(1)){
                        flag = true;
                    }
                }
                if(!flag){
                    courses.remove(course);
                }
            }
            map.addAttribute("client_courses", courses);
        } else {
            map.addAttribute("client_courses", null);
        }

        map.addAttribute("courses", cDao.findAll());

        return "pages/people/home";
    }

    @GetMapping("/meuscursos")
    public String peopleMeusCursos(ModelMap map) {
        return "pages/people/meuscursos";
    }

    @GetMapping("/todoscursos")
    public String peopleTodosCursos(ModelMap map) {
        return "pages/people/todoscursos";
    }

    @GetMapping("/cadastrarcartao")
    public String cadastrarCartao(ModelMap map) {
        return "pages/people/cadastrarcartao";
    }

    @GetMapping("/meusdados")
    public String meusdados(ModelMap map) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account a = aDao.findByEmail(username);

        Person p = (Person) a.getClient();

        map.addAttribute("people", p);
        return "pages/people/meusdados";
    }

    @GetMapping("/editarPessoa/{id}")
    public String editarPessoa(ModelMap map, @PathVariable Long id) {
        Person p = pDao.findById(id).orElse(null);
        map.addAttribute("person", p);

        return "pages/people/editarPessoa";
    }

    @PostMapping("/editarPessoa/{id}")
    public String alterarPessoa(@PathVariable("id") Long id, @ModelAttribute Person person, RedirectAttributes attr) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account a = aDao.findByEmail(username);
        person.setId(id);
        person.setAccount(a);
        pDao.save(person);
        attr.addFlashAttribute("success", "Pessoa editada com sucesso!");
        return ("redirect:/people/meusdados");
    }

    @ModelAttribute("username")
    public String getUsername() {
        String nome = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            nome = userDetails.getUsername();
        }
        return nome;
    }
}
