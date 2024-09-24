package br.edu.ifsp.dsw3.trabalho.empresa.api_controller;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PayRequestDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.PayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pay-requests")
public class PayRequestRestController {

    @Autowired
    private PayRequestDAO payRequestDAO;

    // Buscar todas as requisições de pagamento
    @GetMapping
    public ResponseEntity<List<PayRequest>> getAllPayRequests() {
        List<PayRequest> payRequests = payRequestDAO.findAll();
        return ResponseEntity.ok(payRequests);
    }

    // Buscar requisição de pagamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<PayRequest> getPayRequestById(@PathVariable Long id) {
        Optional<PayRequest> payRequest = payRequestDAO.findById(id);
        return payRequest.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar uma nova requisição de pagamento
    @PostMapping
    public ResponseEntity<PayRequest> createPayRequest(@RequestBody PayRequest payRequest) {
        PayRequest savedPayRequest = payRequestDAO.save(payRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayRequest);
    }

    // Atualizar uma requisição de pagamento existente
    @PutMapping("/{id}")
    public ResponseEntity<PayRequest> updatePayRequest(@PathVariable Long id, @RequestBody PayRequest updatedPayRequest) {
        Optional<PayRequest> optionalPayRequest = payRequestDAO.findById(id);
        if (optionalPayRequest.isPresent()) {
            PayRequest payRequest = optionalPayRequest.get();
            // Atualizar os campos necessários
            payRequest.setDate(updatedPayRequest.getDate());
            payRequest.setCost(updatedPayRequest.getCost());
            payRequest.setCompany(updatedPayRequest.getCompany());
            payRequest.setRequest(updatedPayRequest.getRequest());

            PayRequest savedPayRequest = payRequestDAO.save(payRequest);
            return ResponseEntity.ok(savedPayRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar uma requisição de pagamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayRequest(@PathVariable Long id) {
        Optional<PayRequest> payRequest = payRequestDAO.findById(id);
        if (payRequest.isPresent()) {
            payRequestDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
