package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="idClient")
@Table(name = "person")
public class Person extends Client{
    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(name = "birth_date", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "person")
    private List<PayCourse> pays;

    public Person(Long id, String name, String telephone, String address, Account account, List<Card> cards, String cpf,
            LocalDate birthDate, List<PayCourse> pays) {
        super(id, name, telephone, address, account, cards);
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.pays = pays;
    }

    public Person(String name, String telephone, String address, Account account, List<Card> cards, String cpf,
            LocalDate birthDate, List<PayCourse> pays) {
        super(name, telephone, address, account, cards);
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.pays = pays;
    }

    public Person(Long id, String name, String telephone, String address, Account account, List<Card> cards, String cpf,
            LocalDate birthDate) {
        super(id, name, telephone, address, account, cards);
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public Person(String name, String telephone, String address, Account account, List<Card> cards, String cpf,
            LocalDate birthDate) {
        super(name, telephone, address, account, cards);
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public Person(String cpf, LocalDate birthDate) {
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public Person() {
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

    public List<PayCourse> getPays() {
        return pays;
    }

    public void setPays(List<PayCourse> pays) {
        this.pays = pays;
    }

}
