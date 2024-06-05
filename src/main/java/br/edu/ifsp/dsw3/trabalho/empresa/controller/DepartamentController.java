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

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    @Autowired
    private DepartamentDAO dao;

    @Autowired
    private WorkerDAO daoW;

    private String erro = "Ocorreu um erro, verifique se todos os dados estão corretos";
    
    @PostMapping("/register")
    public Departament cadastrarDepartament(@RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
        return dao.save(new Departament(address, name, description));
    }

    @GetMapping("/listar")
    public List<Departament> listar(){
        return dao.findAll();
    }

    @GetMapping("/pesquisar/{id}")
    public Departament pesquisarCodigo(@PathVariable ("id") Long id){
        if (dao.existsById(id)){
            return dao.findById(id).get();
        } else{
            System.out.println(erro);
            return null;
        }
    }

    @DeleteMapping("/remover/{id}")
    public Boolean remover(@PathVariable("id") Long id){
        if (dao.existsById(id)){
            if(daoW.buscarWorkers(id).isEmpty()){
                dao.deleteById(id);
                return true;
            }
            else{
                daoW.excluirWorkers(id);
                dao.deleteById(id);
                return true;
            }
        } 
        else{
            System.out.println(erro);
            return false;
        }
    }

    @PutMapping("/editar/{id}")
    public Boolean editar(@PathVariable ("id") Long id, @RequestParam(value = "address") String address, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description){
        if (dao.existsById(id)){
            dao.updateDepartament(id, address, name, description);
            return true;
        } else{
            System.out.println(erro);
            return false;
        }
    }

    // Consultas específicas (3)
    // Pesquisa os Departamentos por parte do nome dele
    @GetMapping("/pesquisarNome/{name}")
    public List<Departament> pesquisarNome(@PathVariable ("name") String name){
        return dao.findByName(name);
    }

    // Lista os trabalhadores de um departamento pelo id do departamento
    @GetMapping("/pesquisarWorkers/{id}")
    public List<Worker> pesquisarWorkers(@PathVariable ("id") Long id){
        return daoW.buscarWorkers(id);
    }

    // Pesquisa os Departamentos por parte da descrição dele
    @GetMapping("/pesquisarDescription/{description}")
    public List<Departament> pesquisarDescription(@PathVariable ("description") String description){
        return dao.findByDescription(description);
    }
}
