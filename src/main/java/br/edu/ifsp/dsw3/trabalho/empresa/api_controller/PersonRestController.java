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

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PersonDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Person;

@RestController
@RequestMapping("/api/persons")
public class PersonRestController {

    @Autowired
    private PersonDAO personDAO;

    // Buscar todas as pessoas
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personDAO.findAll();
        return ResponseEntity.ok(persons);
    }

    // Buscar pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personDAO.findById(id);
        return person.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar uma nova pessoa
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personDAO.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    // Atualizar uma pessoa existente
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Optional<Person> optionalPerson = personDAO.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            // Atualizar os campos necessários
            person.setName(updatedPerson.getName());
            person.setTelephone(updatedPerson.getTelephone());
            person.setAddress(updatedPerson.getAddress());
            person.setAccount(updatedPerson.getAccount());
            person.setCards(updatedPerson.getCards());
            person.setCpf(updatedPerson.getCpf());
            person.setBirthDate(updatedPerson.getBirthDate());
            person.setPays(updatedPerson.getPays());

            Person savedPerson = personDAO.save(person);
            return ResponseEntity.ok(savedPerson);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar uma pessoa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        Optional<Person> person = personDAO.findById(id);
        if (person.isPresent()) {
            personDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
