package br.edu.ifsp.dsw3.trabalho.empresa.api_controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;

@RestController
@RequestMapping("/api/workers")
public class WorkerRestController {

    @Autowired
    private WorkerDAO workerDAO;

    // Buscar todos os workers
    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        List<Worker> workers = workerDAO.findAll();
        return ResponseEntity.ok(workers);
    }

    // Buscar worker por ID
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Long id) {
        Optional<Worker> worker = workerDAO.findById(id);
        return worker.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar um novo worker
    @PostMapping
    public ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
        Worker savedWorker = workerDAO.save(worker);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWorker);
    }

    // Atualizar um worker existente
    @PutMapping("/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable Long id, @RequestBody Worker updatedWorker) {
        Optional<Worker> optionalWorker = workerDAO.findById(id);
        if (optionalWorker.isPresent()) {
            Worker worker = optionalWorker.get();
            // Atualizar os campos necessários
            worker.setName(updatedWorker.getName());
            worker.setCpf(updatedWorker.getCpf());
            worker.setRole(updatedWorker.getRole());
            worker.setSalary(updatedWorker.getSalary());
            worker.setBirthDate(updatedWorker.getBirthDate());
            worker.setAddress(updatedWorker.getAddress());
            worker.setTelephone(updatedWorker.getTelephone());
            worker.setAccount(updatedWorker.getAccount());
            worker.setDepartment(updatedWorker.getDepartment());
            worker.setVulnerabilities(updatedWorker.getVulnerabilities());

            Worker savedWorker = workerDAO.save(worker);
            return ResponseEntity.ok(savedWorker);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar um worker
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Long id) {
        Optional<Worker> worker = workerDAO.findById(id);
        if (worker.isPresent()) {
            workerDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
