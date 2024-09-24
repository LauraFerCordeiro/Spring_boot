package br.edu.ifsp.dsw3.trabalho.empresa.api_controller;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CardDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardRestController {

    @Autowired
    private CardDAO cardDAO;

    // Buscar todos os cartões
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardDAO.findAll();
        return ResponseEntity.ok(cards);
    }

    // Buscar cartão por ID
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Optional<Card> card = cardDAO.findById(id);
        return card.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar um novo cartão
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardDAO.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCard);
    }

    // Atualizar um cartão existente
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card updatedCard) {
        Optional<Card> optionalCard = cardDAO.findById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setHolderName(updatedCard.getHolderName());
            card.setNumber(updatedCard.getNumber());
            card.setCvv(updatedCard.getCvv());
            card.setDueDate(updatedCard.getDueDate());
            card.setClient(updatedCard.getClient());

            Card savedCard = cardDAO.save(card);
            return ResponseEntity.ok(savedCard);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar um cartão
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        Optional<Card> card = cardDAO.findById(id);
        if (card.isPresent()) {
            cardDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
