package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends Client<Long>{
    @Column(nullable = false, unique = true)
    private String cnpj;

    public Company(String name, String email, String password, String telephone, String address, String cnpj) {
        super(name, email, password, telephone, address);
        this.cnpj = cnpj;
    }

    public Company(Long id, String name, String email, String password, String telephone, String address, String cnpj) {
        super(id, name, email, password, telephone, address);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
