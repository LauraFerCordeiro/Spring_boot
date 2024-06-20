package br.edu.ifsp.dsw3.trabalho.empresa.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.ClientDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Client;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;

public class CourseService {
    @Autowired
    private CourseDAO dao;

    @Autowired
    private ClientDAO daoC;

    public Course register(String name, BigDecimal value, String description, LocalDate endDate){
        return dao.save(new Course(value, name, description, endDate));
    }

    public List<Course> list(){
        return dao.findAll();
    }

    public Course searchCod(Long id){
        return dao.findById(id).orElse(null);
    }

    public boolean delete(Long id){
        if(dao.existsById(id)){
            if(daoC.findClientsByCourse(id).isEmpty()){
                dao.deleteById(id);
                return true;
            }else{
                daoC.removeCourseId(id);
                dao.deleteById(id);
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean edit(Long id, String name, BigDecimal value, String description, LocalDate endDate){
        if(dao.existsById(id)){
            dao.updateCourse(id, name, value, description, endDate);
            return true;
        }else{
            return false;
        }
    }

    public List<Client> listClients(Long id){
        return daoC.findClientsByCourse(id);
    }
}
