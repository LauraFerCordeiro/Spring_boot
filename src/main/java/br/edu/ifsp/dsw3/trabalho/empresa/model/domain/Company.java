package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    public Company(Long id, String name, String telephone, String address, Account account, List<Card> cards,
            String cnpj, List<PayRequest> pays) {
        super(id, name, telephone, address, account, cards);
        this.cnpj = cnpj;
        this.pays = pays;
    }

    public Company(Long id, String name, String telephone, String address, Account account, List<Card> cards,
            String cnpj) {
        super(id, name, telephone, address, account, cards);
        this.cnpj = cnpj;
    }

    public Company(String name, String telephone, String address, Account account, List<Card> cards, String cnpj,
            List<PayRequest> pays) {
        super(name, telephone, address, account, cards);
        this.cnpj = cnpj;
        this.pays = pays;
    }

    public Company(String name, String telephone, String address, Account account, List<Card> cards, String cnpj) {
        super(name, telephone, address, account, cards);
        this.cnpj = cnpj;
    }

    public Company(Long id, String name, String telephone, String address, Account account, List<Card> cards) {
        super(id, name, telephone, address, account, cards);
    } 

    public Company() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<PayRequest> getPays() {
        return pays;
    }

    public void setPays(List<PayRequest> pays) {
        this.pays = pays;
    }

}
