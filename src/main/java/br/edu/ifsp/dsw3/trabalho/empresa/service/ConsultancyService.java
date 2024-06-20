package br.edu.ifsp.dsw3.trabalho.empresa.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.ClientDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.ConsultancyDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Client;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Consultancy;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;

@Service
public class ConsultancyService {
    @Autowired
    private ConsultancyDAO dao;

    @Autowired
    private ClientDAO daoC;

    @Autowired
    private WorkerDAO daoW;

    public Consultancy register(BigDecimal value, LocalDate endDate, String description, Long clientId,
            Long workerId) {
        if (daoC.existsById(clientId) && daoW.existsById(workerId)) {
            Client c = daoC.findById(clientId).get();
            Worker w = daoW.findById(workerId).get();
            return dao.save(new Consultancy(value, endDate, description, c, w));
        } else {
            return null;
        }
    }

    public List<Consultancy> list() {
        return dao.findAll();
    }

    public Consultancy searchCod(Long id) {
        return dao.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean edit(Long id, BigDecimal value, LocalDate endDate, String description, Long clientId,
            Long workerId) {
        if (dao.existsById(id)) {
            if (daoC.existsById(clientId) && daoW.existsById(workerId)) {
                Client c = daoC.findById(clientId).get();
                Worker w = daoW.findById(workerId).get();
                dao.updateConsultancy(workerId, value, endDate, description, c, w);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
