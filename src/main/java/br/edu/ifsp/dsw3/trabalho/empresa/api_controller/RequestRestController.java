package br.edu.ifsp.dsw3.trabalho.empresa.api_controller;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.RequestDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestRestController {

    @Autowired
    private RequestDAO requestDAO;

    // Buscar todas as requisições
    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestDAO.findAll();
        return ResponseEntity.ok(requests);
    }

    // Buscar requisição por ID
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Optional<Request> request = requestDAO.findById(id);
        return request.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar uma nova requisição
    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request savedRequest = requestDAO.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
    }

    // Atualizar uma requisição existente
    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request updatedRequest) {
        Optional<Request> optionalRequest = requestDAO.findById(id);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            // Atualizar os campos necessários
            request.setName(updatedRequest.getName());
            request.setDescription(updatedRequest.getDescription());
            request.setStartDate(updatedRequest.getStartDate());
            request.setEndDate(updatedRequest.getEndDate());
            request.setPriority(updatedRequest.getPriority());
            request.setStatus(updatedRequest.getStatus());
            request.setCostEstimate(updatedRequest.getCostEstimate());
            request.setVulnerabilities(updatedRequest.getVulnerabilities());
            request.setPay(updatedRequest.getPay());

            Request savedRequest = requestDAO.save(request);
            return ResponseEntity.ok(savedRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar uma requisição
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        Optional<Request> request = requestDAO.findById(id);
        if (request.isPresent()) {
            requestDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
