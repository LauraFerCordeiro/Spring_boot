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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import br.edu.ifsp.dsw3.trabalho.empresa.model.service.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerService service;

    @PostMapping("/register")
    public ResponseEntity<Worker> registerWorker(@RequestBody Worker worker) {
        Worker createdWorker = service.registerWorker(worker.getName(), worker.getEmail(), worker.getBirthDate(), worker.getSalary(), worker.getRole(), worker.getDepartament().getId());
        if (createdWorker != null) {
            return new ResponseEntity<>(createdWorker, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Worker>> list() {
        List<Worker> workers = service.list();
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Worker> searchCod(@PathVariable Long id) {
        Worker worker = service.searchCod(id);
        if (worker != null) {
            return new ResponseEntity<>(worker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(@PathVariable Long id, @RequestBody Worker worker) {
        boolean updated = service.edit(id, worker.getName(), worker.getEmail(), worker.getBirthDate(), worker.getSalary(), worker.getRole(), worker.getDepartament().getId());
        if (updated) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<List<Worker>> searchName(@PathVariable String name) {
        List<Worker> workers = service.searchName(name);
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping("/salary")
    public ResponseEntity<List<Worker>> searchSalary() {
        List<Worker> workers = service.searchSalary();
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping("/role")
    public ResponseEntity<List<Worker>> searchRole(@RequestParam String role) {
        List<Worker> workers = service.searchRole(role);
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }
}