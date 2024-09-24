package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PayRequestDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.RequestDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.VulnerabilityDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Request;

@Controller
@RequestMapping("/requests")
public class RequestController {
    @Autowired
    RequestDAO rDao;

    @Autowired
    VulnerabilityDAO vDao;

    @Autowired
    PayRequestDAO pDao;

    @GetMapping("/cadastrar")
    public String cadastrar(Request request){
        return("/requests/cadastro");
    }

    @GetMapping("/listar")
    public String listar(ModelMap map){
        map.addAttribute("requests", rDao.findAll());
        return ("/requests/lista");
    }

    @PostMapping("/salvar")
    public String salvar(Request request){
        rDao.save(request);
        return("redirect:/requests/cadastrar");
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id")Long id ){
        map.addAttribute("request", rDao.getReferenceById(id));
        return ("/requests/editar");
    }

    @PostMapping("/editar")
    public String alterar(Request request, RedirectAttributes attr){
        rDao.save(request);
        attr.addFlashAttribute("success", "Pedido editado com sucesso!");
        return("redirect:/requests/listar");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, ModelMap map){
        if(!(pDao.findPaysByRequestId(id).isEmpty())){
            pDao.deletePaysByRequestId(id);
        }
        if(!(vDao.findVulnerabilitiesByRequestId(id).isEmpty())){
            vDao.deleteVulnerabilitiesByRequestId(id);
        }

        rDao.deleteById(id);
        map.addAttribute("success", "Pedido excluído com sucesso!");
        return listar(map);
    }

}
