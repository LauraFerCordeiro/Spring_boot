package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.edu.ifsp.dsw3.trabalho.empresa.service.DepartamentService;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    @Autowired
    private DepartamentService departamentService;

    @PostMapping("/register")
    public ResponseEntity<Departament> registerDepartament(
            @RequestParam(value = "address") String address,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description) {
        Departament departament = departamentService.registerDepartament(address, name, description);
        return new ResponseEntity<>(departament, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Departament>> list() {
        List<Departament> departaments = departamentService.list();
        return new ResponseEntity<>(departaments, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Departament> searchCod(@PathVariable("id") Long id) {
        Departament departament = departamentService.searchCod(id);
        if (departament != null) {
            return new ResponseEntity<>(departament, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        boolean deleted = departamentService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(
            @PathVariable("id") Long id,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description) {
        boolean edited = departamentService.edit(id, address, name, description);
        if (edited) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchName")
    public ResponseEntity<List<Departament>> searchName(@RequestParam("name") String name) {
        List<Departament> departaments = departamentService.searchName(name);
        return new ResponseEntity<>(departaments, HttpStatus.OK);
    }

    @GetMapping("/searchWorkers/{id}")
    public ResponseEntity<List<Object[]>> searchWorkers(@PathVariable("id") Long id) {
        List<Object[]> workers = departamentService.searchWorkers(id);
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping("/searchDescription")
    public ResponseEntity<List<Departament>> searchDescription(@RequestParam("description") String description) {
        List<Departament> departaments = departamentService.searchDescription(description);
        return new ResponseEntity<>(departaments, HttpStatus.OK);
    }
}

