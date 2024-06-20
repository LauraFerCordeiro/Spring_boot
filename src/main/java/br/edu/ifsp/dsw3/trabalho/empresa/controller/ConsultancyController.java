package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Consultancy;
import br.edu.ifsp.dsw3.trabalho.empresa.service.ConsultancyService;

@RestController
@RequestMapping("/consultancy")
public class ConsultancyController {

    @Autowired
    private ConsultancyService consultancyService;

    @PostMapping("/register")
    public ResponseEntity<Consultancy> registerConsultancy(
            @RequestParam(value = "value") BigDecimal value,
            @RequestParam(value = "endDate") LocalDate endDate,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "clientId") Long clientId,
            @RequestParam(value = "workerId") Long workerId) {
        Consultancy consultancy = consultancyService.register(value, endDate, description, clientId, workerId);
        if (consultancy != null) {
            return new ResponseEntity<>(consultancy, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Consultancy>> list() {
        List<Consultancy> consultancies = consultancyService.list();
        return new ResponseEntity<>(consultancies, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Consultancy> searchCod(@PathVariable("id") Long id) {
        Consultancy consultancy = consultancyService.searchCod(id);
        if (consultancy != null) {
            return new ResponseEntity<>(consultancy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        boolean deleted = consultancyService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(
            @PathVariable("id") Long id,
            @RequestParam(value = "value") BigDecimal value,
            @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "clientId") Long clientId,
            @RequestParam(value = "workerId") Long workerId) {
        boolean edited = consultancyService.edit(id, value, endDate, description, clientId, workerId);
        if (edited) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
