package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToOne(mappedBy = "client")
    private Consultancy consultancy;

    @ManyToOne
    @JoinColumn(name = "department_id_fk")
    @JsonManagedReference
    private Departament department;

    public Client(Long id, String name, String email, Consultancy consultancy, Departament department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.consultancy = consultancy;
        this.department = department;
    }

    public Client(String name, String email, Consultancy consultancy, Departament department) {
        this.name = name;
        this.email = email;
        this.consultancy = consultancy;
        this.department = department;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Consultancy getConsultancy() {
        return consultancy;
    }

    public void setConsultancy(Consultancy consultancy) {
        this.consultancy = consultancy;
    }

    public Departament getDepartment() {
        return department;
    }

    public void setDepartment(Departament department) {
        this.department = department;
    }

    
}