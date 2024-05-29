package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartamentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    @Autowired
    private DepartamentDAO dao;
    
    @PostMapping("/register")
    public Departament cadastrarDepartament(@RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
        return dao.save(new Departament(address, name, description));
    }

    @GetMapping("/listar")
    public List<Departament> listar(){
        return dao.findAll();
    }

    @GetMapping("/pesquisar/{id}")
    public Departament pesquisarCodigo(@PathVariable ("id") Long id){
        return dao.findById(id).get();
    }
}
