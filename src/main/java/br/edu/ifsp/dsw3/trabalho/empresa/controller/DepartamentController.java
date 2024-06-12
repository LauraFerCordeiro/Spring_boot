package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;
import br.edu.ifsp.dsw3.trabalho.empresa.model.service.DepartamentService;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    @Autowired
    private DepartamentService departamentService;

    @PostMapping("/register")
    public ResponseEntity<Departament> registerDepartament(@RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
        return departamentService.registerDepartament(address, name, description);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Departament>>list(){
        return departamentService.list();
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Departament> searchCod(@PathVariable ("id") Long id){
        return departamentService.searchCod(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        return departamentService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(@PathVariable ("id") Long id, @RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
        return departamentService.edit(id, address, name, description);
    }

    // Consultas específicas (3)
    // Pesquisa os Departamentos por parte do nome dele
    @GetMapping("/searchName")
    public ResponseEntity<List<Departament>> searchName(@RequestParam ("name") String name){
        return departamentService.searchName(name);
    }

    // Lista os trabalhadores de um departamento pelo id do departamento
    @GetMapping("/searchWorkers/{id}")
    public ResponseEntity<List<Object[]>> searchWorkers(@PathVariable ("id") Long id){
        return departamentService.searchWorkers(id);
    }

    // Pesquisa os Departamentos por parte da descrição dele
    @GetMapping("/searchDescription")
    public ResponseEntity<List<Departament>> searchDescription(@RequestParam ("description") String description){
        return departamentService.searchDescription(description);
    }
}
