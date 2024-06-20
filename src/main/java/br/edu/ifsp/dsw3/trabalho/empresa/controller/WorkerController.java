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

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import br.edu.ifsp.dsw3.trabalho.empresa.service.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @PostMapping("/register")
    public ResponseEntity<Worker> registerWorker(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "birthDate") LocalDate birthDate,
            @RequestParam(value = "salary") BigDecimal salary,
            @RequestParam(value = "role") String role,
            @RequestParam(value = "departamentId") Long departamentId) {
        Worker worker = workerService.registerWorker(name, email, birthDate, salary, role, departamentId);
        if (worker != null) {
            return new ResponseEntity<>(worker, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Worker>> list() {
        List<Worker> workers = workerService.list();
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Worker> searchCod(@PathVariable("id") Long id) {
        Worker worker = workerService.searchCod(id);
        if (worker != null) {
            return new ResponseEntity<>(worker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        boolean deleted = workerService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(
            @PathVariable("id") Long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "birthDate") LocalDate birthDate,
            @RequestParam(value = "salary") BigDecimal salary,
            @RequestParam(value = "role") String role,
            @RequestParam(value = "departamentId") Long departamentId) {
        boolean edited = workerService.edit(id, name, email, birthDate, salary, role, departamentId);
        if (edited) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchName")
    public ResponseEntity<List<Worker>> searchName(@RequestParam("name") String name) {
        List<Worker> workers = workerService.searchName(name);
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping("/searchSalary")
    public ResponseEntity<List<Worker>> searchSalary() {
        List<Worker> workers = workerService.searchSalary();
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping("/searchRole")
    public ResponseEntity<List<Worker>> searchRole(@RequestParam("role") String role) {
        List<Worker> workers = workerService.searchRole(role);
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }
}
