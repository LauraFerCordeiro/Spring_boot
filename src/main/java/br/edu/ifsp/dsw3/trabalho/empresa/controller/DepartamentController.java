package br.edu.ifsp.dsw3.trabalho.empresa.controller;

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
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    @Autowired
    private DepartamentDAO dao;

    @Autowired
    private WorkerDAO daoW;

    // Faltando: um tratamento de erro mais adequado com try catch e exception
    //private String erro = "Ocorreu um erro, verifique se todos os dados estão corretos";
    
    @PostMapping("/register")
    public Departament registerDepartament(@RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
        return dao.save(new Departament(address, name, description));
    }

    @GetMapping("/list")
    public List<Departament> list(){
        return dao.findAll();
    }

    @GetMapping("/search/{id}")
    public Departament searchCod(@PathVariable ("id") Long id){
        if (dao.existsById(id)){
            return dao.findById(id).get();
        } else{
            // Pesquisar um comando mais adequado para notificar o usuário sobre o erro
            // System.out.println(erro);
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") Long id){
        if (dao.existsById(id)){
            if(daoW.findWorkers(id).isEmpty()){
                dao.deleteById(id);
                return true;
            }
            else{
                daoW.deleteWorkers(id);
                dao.deleteById(id);
                return true;
            }
        } 
        else{
            // System.out.println(erro);
            return false;
        }
    }

    @PutMapping("/edit/{id}")
    public Boolean edit(@PathVariable ("id") Long id, @RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
        if (dao.existsById(id)){
            dao.updateDepartament(id, address, name, description);
            return true;
        } else{
            // System.out.println(erro);
            return false;
        }
    }

    // Consultas específicas (3)
    // Pesquisa os Departamentos por parte do nome dele
    @GetMapping("/searchName/{name}")
    public List<Departament> searchName(@PathVariable ("name") String name){
        return dao.findByName(name);
    }

    // Lista os trabalhadores de um departamento pelo id do departamento
    @GetMapping("/searchWorkers/{id}")
    public List<Worker> searchWorkers(@PathVariable ("id") Long id){
        return daoW.findWorkers(id);
    }

    // Pesquisa os Departamentos por parte da descrição dele
    @GetMapping("/searchDepartament")
    public List<Departament> searchDepartament(@PathParam ("description") String description){
        return dao.findByDescription(description);
    }
}
