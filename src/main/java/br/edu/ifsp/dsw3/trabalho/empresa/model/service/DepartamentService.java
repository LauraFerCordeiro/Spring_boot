package br.edu.ifsp.dsw3.trabalho.empresa.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartamentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;

@Service
public class DepartamentService {
    @Autowired
    private DepartamentDAO dao;
    
    @Autowired
    private WorkerDAO daoW;

    public ResponseEntity<Departament> registerDepartament(String address, String name, String description){
        Departament departament = dao.save(new Departament(address, name, description));
        return new ResponseEntity<>(departament, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Departament>> list(){
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Departament> searchCod(Long id){
        if(dao.existsById(id)){
            return new ResponseEntity<>(dao.findById(id).get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> delete(Long id){
        if(dao.existsById(id)){
            if(daoW.findWorkers(id).isEmpty()){
                dao.deleteById(id);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                daoW.deleteWorkers(id);
                dao.deleteById(id);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> edit(Long id, String address, String name, String description){
        if(dao.existsById(id)){
            dao.updateDepartament(id, address, name, description);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Departament>> searchName(String name){
        return ResponseEntity.ok(dao.findByName(name));
    }

    public ResponseEntity<List<Object[]>> searchWorkers(Long id){
        return ResponseEntity.ok(daoW.findWorkersDepartament(id));
    }

    public ResponseEntity<List<Departament>> searchDescription(String description){
        return ResponseEntity.ok(dao.findByDescription(description));
    }
}
