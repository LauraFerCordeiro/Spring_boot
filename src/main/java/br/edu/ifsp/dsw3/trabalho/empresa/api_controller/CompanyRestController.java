package br.edu.ifsp.dsw3.trabalho.empresa.api_controller;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CompanyDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {

    @Autowired
    private CompanyDAO companyDAO;

    // Buscar todas as empresas
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyDAO.findAll();
        return ResponseEntity.ok(companies);
    }

    // Buscar empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyDAO.findById(id);
        return company.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar uma nova empresa
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company savedCompany = companyDAO.save(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    // Atualizar uma empresa existente
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        Optional<Company> optionalCompany = companyDAO.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            // Atualizar os campos necessários
            company.setName(updatedCompany.getName());
            company.setTelephone(updatedCompany.getTelephone());
            company.setAddress(updatedCompany.getAddress());
            company.setAccount(updatedCompany.getAccount());
            company.setCards(updatedCompany.getCards());
            company.setCnpj(updatedCompany.getCnpj());
            company.setPays(updatedCompany.getPays());
            // Atualizar requests se necessário

            Company savedCompany = companyDAO.save(company);
            return ResponseEntity.ok(savedCompany);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar uma empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        Optional<Company> company = companyDAO.findById(id);
        if (company.isPresent()) {
            companyDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

