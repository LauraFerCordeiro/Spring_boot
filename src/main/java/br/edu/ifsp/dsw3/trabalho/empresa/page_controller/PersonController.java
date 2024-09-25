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
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PersonDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Person;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/people")
public class PersonController {
    @Autowired
    PersonDAO pDao;

    @Autowired
    PayCourseDAO pCDao;

    @Autowired
    CourseDAO cDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Person person, HttpSession session) {
        String response = verificaSession(session, 4);
        if (!(response.equals("correto"))) {
            return response;
        }

        return ("pages/people/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map, HttpSession session) {
        String response = verificaSession(session, 4);
        if (!(response.equals("correto"))) {
            return response;
        }

        map.addAttribute("people", pDao.findAll());
        return ("pages/people/lista");
    }

    @PostMapping("/salvar")
    public String salvar(Person person) {
        pDao.save(person);
        return ("redirect:/people/cadastrar");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id") Long id, HttpSession session) {
        String response = verificaSession(session, 4);
        if (!(response.equals("correto"))) {
            return response;
        }

        map.addAttribute("person", pDao.getReferenceById(id));
        return ("/people/editar");
    }

    @PostMapping("/editar")
    public String alterar(Person person, RedirectAttributes attr, HttpSession session) {
        String response = verificaSession(session, 4);
        if (!(response.equals("correto"))) {
            return response;
        }

        pDao.save(person);
        attr.addFlashAttribute("success", "Pessoa editada com sucesso!");
        return ("redirect:/people/lista");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap map, HttpSession session) {
        String response = verificaSession(session, 4);
        if (!(response.equals("correto"))) {
            return response;
        }

        if (pCDao.findPaysByPersonId(id).isEmpty()) {
            pDao.deleteById(id);
        } else {
            pCDao.deletePaysByPersonId(id);
            pDao.deleteById(id);
        }

        map.addAttribute("success", "Pessoa excluída com sucesso!");
        return listar(map, session);
    }

    @GetMapping("/home")
    public String home(HttpSession session, ModelMap map) {
        String response = verificaSession(session, 1);
        if (!(response.equals("correto"))) {
            return response;
        }

        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            map.addAttribute("loggedInUser", loggedInUser);
        }

        return "pages/people/home";
    }

    @GetMapping("/meuscursos")
    public String peopleMeusCursos(HttpSession session, ModelMap map) {
        String response = verificaSession(session, 1);
        if (!(response.equals("correto"))) {
            return response;
        }

        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            map.addAttribute("loggedInUser", loggedInUser);
        }

        return "pages/people/meuscursos";
    }

    @GetMapping("/todoscursos")
    public String peopleTodosCursos(HttpSession session, ModelMap map) {
        String response = verificaSession(session, 1);
        if (!(response.equals("correto"))) {
            return response;
        }

        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            map.addAttribute("loggedInUser", loggedInUser);
        }

        return "pages/people/todoscursos";
    }

    private String verificaSession(HttpSession session, Integer view) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login";
        } else if (loggedInUser.getView() != view) {
            switch (loggedInUser.getView()) {
                case 1:
                    return "redirect:/people/home";
                case 2:
                    return "redirect:/companies/home";
                case 3:
                    return "redirect:/workers/home";
                case 4:
                    return "redirect:/admin/home";
                default:
                    break;
            }
        }

        return "correto";
    }
}
