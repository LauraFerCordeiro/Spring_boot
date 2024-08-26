package br.edu.ifsp.dsw3.trabalho.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartamentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Department;

@Service
public class DepartamentService {
    @Autowired
    private DepartamentDAO dao;

    @Autowired
    private WorkerDAO daoW;

    public Department registerDepartament(String address, String name, String description) {
        return dao.save(new Department(address, name, description));
    }

    public List<Department> list() {
        return dao.findAll();
    }

    public Department searchCod(Long id) {
        return dao.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (dao.existsById(id)) {
            if (daoW.findWorkers(id).isEmpty()) {
                dao.deleteById(id);
                return true;
            } else {
                daoW.deleteWorkers(id);
                dao.deleteById(id);
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean edit(Long id, String address, String name, String description) {
        if (dao.existsById(id)) {
            dao.updateDepartament(id, address, name, description);
            return true;
        } else {
            return false;
        }
    }

    public List<Department> searchName(String name) {
        return dao.findByName(name);
    }

    public List<Object[]> searchWorkers(Long id) {
        return daoW.findWorkersDepartament(id);
    }

    public List<Department> searchDescription(String description) {
        return dao.findByDescription(description);
    }
}

