package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToOne(mappedBy = "client")
    @JsonBackReference
    private Consultancy consultancy;

    @ManyToOne
    @JoinColumn(name = "course")
    @JsonManagedReference
    private Course course;

    public Client(Long id, String name, String email, Consultancy consultancy, Course course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.consultancy = consultancy;
        this.course = course;
    }

    public Client(String name, String email, Consultancy consultancy, Course course) {
        this.name = name;
        this.email = email;
        this.consultancy = consultancy;
        this.course = course;
    }

    public Client(String name, String email, Consultancy consultancy) {
        this.name = name;
        this.email = email;
        this.consultancy = consultancy;
    }

    public Client(String name, String email, Course course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}