package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartamentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    @Autowired
    private DepartamentDAO dao;

    @Autowired
    private WorkerDAO daoW;
    
    @PostMapping("/register")
    public ResponseEntity<Departament> registerDepartament(@RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
        Departament departament = dao.save(new Departament(address, name, description));
        return new ResponseEntity<>(departament, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Departament> list(){
        return dao.findAll();
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Departament> searchCod(@PathVariable ("id") Long id){
        if (dao.existsById(id)){
            return new ResponseEntity<>(dao.findById(id).get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        if (dao.existsById(id)){
            if(daoW.findWorkers(id).isEmpty()){
                dao.deleteById(id);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                daoW.deleteWorkers(id);
                dao.deleteById(id);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        } 
        else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(@PathVariable ("id") Long id, @RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
        if (dao.existsById(id)){
            dao.updateDepartament(id, address, name, description);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    // Consultas específicas (3)
    // Pesquisa os Departamentos por parte do nome dele
    @GetMapping("/searchName")
    public List<Departament> searchName(@RequestParam ("name") String name){
        return dao.findByName(name);
    }

    // Lista os trabalhadores de um departamento pelo id do departamento
    @GetMapping("/searchWorkers/{id}")
    public List<Worker> searchWorkers(@PathVariable ("id") Long id){
        return daoW.findWorkers(id);
    }

    // Pesquisa os Departamentos por parte da descrição dele
    @GetMapping("/searchDepartament")
    public List<Departament> searchDepartament(@RequestParam ("description") String description){
        return dao.findByDescription(description);
    }
}
