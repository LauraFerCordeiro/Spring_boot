package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer view; //{1 : Person, 2 : Company, 3 : Worker, 4 : Admin}

    @Column(nullable = false)
    private Boolean admin;

    @OneToOne(mappedBy = "account")
    private Client client;

    @OneToOne(mappedBy = "account")
    private Worker worker;

    @OneToOne()

    public Account(Long id, String email, String password, String name, Integer view, Boolean admin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.view = view;
        this.admin = admin;
    }

    public Account(String email, String password, String name, Integer view, Boolean admin) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.view = view;
        this.admin = admin;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    
}
