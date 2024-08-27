package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="idClient")
@Table(name = "companies")
public class Company extends Client{
    @Column(nullable = false, unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "company")
    private List<PayRequest> pays;

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
