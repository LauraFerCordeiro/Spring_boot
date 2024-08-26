package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person extends Client<Long>{
    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birthDate;

    public Person(String name, String email, String password, String telephone, String address, String cpf,
            LocalDate birthDate) {
        super(name, email, password, telephone, address);
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public Person(Long id, String name, String email, String password, String telephone, String address, String cpf,
            LocalDate birthDate) {
        super(id, name, email, password, telephone, address);
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    

}
