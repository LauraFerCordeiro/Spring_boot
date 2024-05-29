package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartamentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerDAO dao;

    @Autowired
    private DepartamentDAO daoD;

    @PostMapping("/register")
    public Worker registerWorker(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "birthDate") LocalDate birthDate, @RequestParam(value = "salary") BigDecimal salary, @RequestParam(value = "role") String role, @RequestParam(value = "departamentId") Long departamentId){
        Departament d = daoD.findById(departamentId).get();
        return dao.save(new Worker(name, email, birthDate, salary, role, d));
    }

    @GetMapping("/listar")
    public List<Worker> listar(){
        return dao.findAll();
    }

    @GetMapping("/pesquisar/{id}")
    public Worker pesquisarCodigo(@PathVariable ("id") Long id){
        return dao.findById(id).get();
    }

    @DeleteMapping("/remover/{id}")
    public Boolean remover(@PathVariable ("id") Long id){
        if (dao.existsById(id)){
            dao.deleteById(id);
            return true;
        }
        return false;
    }

}
