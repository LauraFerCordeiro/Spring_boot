package br.edu.ifsp.dsw3.trabalho.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.ClientDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.ConsultancyDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Client;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Consultancy;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;

@Service
public class ClientService {
    @Autowired
    private ClientDAO dao;

    @Autowired
    private CourseDAO daoCo;

    @Autowired
    private ConsultancyDAO daoC;

    public Client register(String name, String email, Long consultancyId, Long courseId) {
        if (consultancyId != null && courseId != null) {

            if (daoC.existsById(consultancyId) && daoCo.existsById(courseId)) {
                Consultancy c = daoC.findById(consultancyId).get();
                Course co = daoCo.findById(courseId).get();
                return dao.save(new Client(name, email, c, co));
            } else {
                return null;
            }

        } else if (consultancyId != null) {

            if (daoC.existsById(consultancyId)) {
                Consultancy c = daoC.findById(consultancyId).get();
                return dao.save(new Client(name, email, c));
            } else {
                return null;
            }

        } else if (courseId != null) {

            if (daoCo.existsById(courseId)) {
                Course co = daoCo.findById(courseId).get();
                return dao.save(new Client(name, email, co));
            } else {
                return null;
            }

        } else {
            return dao.save(new Client(name, email));
        }
    }

    public List<Client> list() {
        return dao.findAll();
    }

    public Client searchCod(Long id) {
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

    public boolean edit(Long id, String name, String email, Long consultancyId, Long courseId) {
        if (dao.existsById(id)) {
            if (consultancyId != null && courseId != null) {

                if (daoC.existsById(consultancyId) && daoCo.existsById(courseId)) {
                    Consultancy c = daoC.findById(consultancyId).get();
                    Course co = daoCo.findById(courseId).get();
                    dao.updateClient(id, name, email, c, co);
                    return true;
                } else {
                    return false;
                }

            } else if (consultancyId != null) {

                if (daoC.existsById(consultancyId)) {
                    Consultancy c = daoC.findById(consultancyId).get();
                    dao.updateClient(id, name, email, c);
                    return true;
                } else {
                    return false;
                }

            } else if (courseId != null) {

                if (daoCo.existsById(courseId)) {
                    Course co = daoCo.findById(courseId).get();
                    dao.updateClient(id, name, email, co);
                    return true;
                } else {
                    return false;
                }

            } else {
                Consultancy consultancy = dao.findById(id).get().getConsultancy();
                Course course = dao.findById(id).get().getCourse();
                if (consultancy == null && course == null) {
                    dao.updateClient(id, name, email);
                    dao.flush();
                    return true;
                } else {
                    return false;
                }
            }
        }else{
            return false; 
        }
    }
}
