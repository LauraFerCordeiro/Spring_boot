package br.edu.ifsp.dsw3.trabalho.empresa.model.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartamentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;

@Service
public class WorkerService {
    @Autowired
    private WorkerDAO dao;
    
    @Autowired
    private DepartamentDAO daoD;

    public ResponseEntity<Worker> registerWorker(String name, String email, LocalDate birthDate, BigDecimal salary, String role, Long departamentId){
        if(daoD.existsById(departamentId)){
            Departament d = daoD.findById(departamentId).get();
            Worker worker = dao.save(new Worker(name, email, birthDate, salary, role, d));
            return new ResponseEntity<>(worker, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Worker>> list(){
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Worker> searchCod(Long id){
        if(dao.existsById(id)){
            return new ResponseEntity<>(dao.findById(id).get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> delete(Long id){
        if(dao.existsById(id)){
            dao.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> edit(Long id, String name, String email, LocalDate birthDate, BigDecimal salary, String role, Long departamentId){
        if(dao.existsById(departamentId)){
            Departament d = daoD.findById(departamentId).get();
            dao.updateWorker(id, name, email, birthDate, salary, role, d);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Worker>> searchName(String name){
        return ResponseEntity.ok(dao.findByName(name));
    }

    public ResponseEntity<List<Worker>> searchSalary(){
        return ResponseEntity.ok(dao.findSalary());
    }

    public ResponseEntity<List<Worker>> searchRole(String role){
        return ResponseEntity.ok(dao.findRole(role));
    }
}
