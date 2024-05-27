package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    // @PostMapping("/cadastrar")
    // public Departament cadastrarDepartament(@RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
    //     return dao.save(new Departament(address, name, description));
    // }
}
