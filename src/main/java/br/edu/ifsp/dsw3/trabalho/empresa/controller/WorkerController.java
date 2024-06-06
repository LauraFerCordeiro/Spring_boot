package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerDAO dao;

    @Autowired
    private DepartamentDAO daoD;

    private String erro = "Ocorreu um erro, verifique se todos os dados estão corretos";

    @PostMapping("/register")
    public Worker registerWorker(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "birthDate") LocalDate birthDate, @RequestParam(value = "salary") BigDecimal salary, @RequestParam(value = "role") String role, @RequestParam(value = "departamentId") Long departamentId){
        if (daoD.existsById(departamentId)){
            Departament d = daoD.findById(departamentId).get();
            return dao.save(new Worker(name, email, birthDate, salary, role, d));
        } else{
            System.out.println(erro);
            return null;
        }
    }

    @GetMapping("/list")
    public List<Worker> list(){
        return dao.findAll();
    }

    @GetMapping("/search/{id}")
    public Worker searchCod(@PathVariable ("id") Long id){
        if (dao.existsById(id)){
            return dao.findById(id).get();
        } else {
            System.out.println(erro);
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable ("id") Long id){
        if (dao.existsById(id)){
            dao.deleteById(id);
            return true;
        } else{
            System.out.println(erro);
            return false;
        }
    }

    @PutMapping("/edit/{id}")
    public Boolean edit(@PathVariable ("id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "birthDate") LocalDate birthDate, @RequestParam(value = "salary") BigDecimal salary, @RequestParam(value = "role") String role, @RequestParam(value = "departamentId") Long departamentId){
        if (dao.existsById(id)){
            Departament d = daoD.findById(departamentId).get();
            dao.updateWorker(id, name, email, birthDate, salary, role, d);
            return true;
        } else{
            System.out.println(erro);
            return false;
        }
    }

    // Consultas específicas (3)
    // Pesquisa Workers por parte do nome 
    @GetMapping("/searchName/{name}")
    public List<Worker> searchName(@PathVariable ("name") String name){
        return dao.findByName(name);
    }

    // Workers do maior salário para o menor salário
    @GetMapping("/searchSalary")
    public List<Worker> searchSalary(){
        return dao.findSalary();
    }

    // Pesquisar por função (role) do Worker ordenado do mais velho para o mais novo
    @GetMapping("/searchRole")
    public List<Worker> searchRole(@RequestParam ("role") String role){
        return dao.findRole(role);
    }


}
