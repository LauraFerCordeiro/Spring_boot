package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import br.edu.ifsp.dsw3.trabalho.empresa.model.service.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @PostMapping("/register")
    public ResponseEntity<Worker> registerWorker(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "birthDate") LocalDate birthDate, @RequestParam(value = "salary") BigDecimal salary, @RequestParam(value = "role") String role, @RequestParam(value = "departamentId") Long departamentId){
        return workerService.registerWorker(name, email, birthDate, salary, role, departamentId);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Worker>> list(){
        return workerService.list();
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Worker> searchCod(@PathVariable ("id") Long id){
        return workerService.searchCod(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Long id){
        return workerService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(@PathVariable ("id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "birthDate") LocalDate birthDate, @RequestParam(value = "salary") BigDecimal salary, @RequestParam(value = "role") String role, @RequestParam(value = "departamentId") Long departamentId){
        return workerService.edit(id, name, email, birthDate, salary, role, departamentId);
    }

    // Consultas específicas (3)
    // Pesquisa Workers por parte do nome  
    @GetMapping("/searchName")
    public ResponseEntity<List<Worker>> searchName(@RequestParam ("name") String name){
        return workerService.searchName(name);
    }

    // Workers do maior salário para o menor salário
    @GetMapping("/searchSalary")
    public ResponseEntity<List<Worker>> searchSalary(){
        return workerService.searchSalary();
    }

    // Pesquisar por função (role) do Worker ordenado do mais velho para o mais novo
    @GetMapping("/searchRole")
    public ResponseEntity<List<Worker>> searchRole(@RequestParam ("role") String role){
        return workerService.searchRole(role);
    }
}
