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

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.ClientDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CompanyDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PayRequestDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.RequestDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.VulnerabilityDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Company;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.PayRequest;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Request;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    
    @Autowired
    CompanyDAO companyDAO;

    @Autowired
    ClientDAO clientDAO;

    @Autowired
    VulnerabilityDAO vulnerabilityDAO;

    @Autowired
    RequestDAO requestDAO;

    @Autowired
    PayRequestDAO payRequestDAO;

    @GetMapping("/cadastrar")
    public String cadastrar(Company company){
        return("pages/companies/cadastrar");
    }

    @GetMapping("/lista")
    public String listar(ModelMap map){
        map.addAttribute("companies", companyDAO.findAll());
        return ("pages/companies/lista");
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Company company) {
        companyDAO.save(company);
        return "redirect:/companies/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap map, @PathVariable("id") Long id) {
        map.addAttribute("company", companyDAO.getReferenceById(id));
        return ("pages/companies/editar");
    }
    
    @PostMapping("/editar/{id}")
    public String alterar(@PathVariable("id") Long id, @ModelAttribute Company company, RedirectAttributes attr) {
        company.setId(id);
        companyDAO.save(company);
        attr.addFlashAttribute("success", "Companhia editada com sucesso!");
        return("redirect:/companies/lista");
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, ModelMap map){
        List<Request> requests = requestDAO.findRequestsByCompanyId(id);
        List<PayRequest> pays = payRequestDAO.findPaysByCompanyId(id);

        if(!(pays.isEmpty())){
            payRequestDAO.deletePaysByCompanyId(id);
        }
        if(requests.isEmpty()){
            requestDAO.deleteRequestsByCompanyId(id);
        }else{
            for (Request request : requests) {
                if(!(vulnerabilityDAO.findVulnerabilitiesByRequestId(request.getId()).isEmpty())){
                    vulnerabilityDAO.deleteVulnerabilitiesByRequestId(request.getId());
                }
            }
            requestDAO.deleteRequestsByCompanyId(id);
        }
        
        companyDAO.deleteById(id);
        clientDAO.deleteById(id);

        map.addAttribute("success", "Compania excluída com sucesso");
        return listar(map);
    }

}
