package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "clients")
public abstract class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false, unique = true)
    protected String telephone;
    
    protected String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    protected Account account;

    @OneToMany(mappedBy = "client")
    protected List<Card> cards;

    protected Client(Long id, String name, String telephone, String address, Account account, List<Card> cards) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.account = account;
        this.cards = cards;
    }

    protected Client(String name, String telephone, String address, Account account, List<Card> cards) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.account = account;
        this.cards = cards;
    }

    protected Client(Long id, String name, String telephone, String address, Account account) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.account = account;
    }

    protected Client(String name, String telephone, String address, Account account) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.account = account;
    }

    protected Client() {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    
}