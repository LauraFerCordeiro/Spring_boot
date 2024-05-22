package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker implements Serializable{
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private BigDecimal salary;
    private String role;
    
    public Worker(String name, String email, LocalDate birthDate, BigDecimal salary, String role) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.salary = salary;
        this.role = role;
    }

    public Worker(Long id, String name, String email, LocalDate birthDate, BigDecimal salary, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.salary = salary;
        this.role = role;
    }

    public Worker() {
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
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}
