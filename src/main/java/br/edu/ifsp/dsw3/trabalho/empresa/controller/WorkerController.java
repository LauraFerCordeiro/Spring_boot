package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartamentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerDAO dao;

    @Autowired
    private DepartamentDAO daoD;

    @PostMapping("/register")
    public ResponseEntity<Worker> registerWorker(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "birthDate") LocalDate birthDate, @RequestParam(value = "salary") BigDecimal salary, @RequestParam(value = "role") String role, @RequestParam(value = "departamentId") Long departamentId){
        if (daoD.existsById(departamentId)){
            Departament d = daoD.findById(departamentId).get();
            Worker worker = dao.save(new Worker(name, email, birthDate, salary, role, d));
            return new ResponseEntity<>(worker, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Worker>> list(){
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Worker> searchCod(@PathVariable ("id") Long id){
        if (dao.existsById(id)){
            return new ResponseEntity<>(dao.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Long id){
        if (dao.existsById(id)){
            dao.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(@PathVariable ("id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "birthDate") LocalDate birthDate, @RequestParam(value = "salary") BigDecimal salary, @RequestParam(value = "role") String role, @RequestParam(value = "departamentId") Long departamentId){
        if (dao.existsById(id)){
            Departament d = daoD.findById(departamentId).get();
            dao.updateWorker(id, name, email, birthDate, salary, role, d);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

    // Consultas específicas (3)
    // Pesquisa Workers por parte do nome  
    @GetMapping("/searchName")
    public ResponseEntity<List<Worker>> searchName(@RequestParam ("name") String name){
        return ResponseEntity.ok(dao.findByName(name));
    }

    // Workers do maior salário para o menor salário
    @GetMapping("/searchSalary")
    public ResponseEntity<List<Worker>> searchSalary(){
        return ResponseEntity.ok(dao.findSalary());
    }

    // Pesquisar por função (role) do Worker ordenado do mais velho para o mais novo
    @GetMapping("/searchRole")
    public ResponseEntity<List<Worker>> searchRole(@RequestParam ("role") String role){
        return ResponseEntity.ok(dao.findRole(role));
    }


}
