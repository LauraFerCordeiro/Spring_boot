package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String holderName;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private Integer cvv;

    @Column(name = "due_date", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Card(Long id, String holderName, String number, Integer cvv, LocalDate dueDate, Client client) {
        this.id = id;
        this.holderName = holderName;
        this.number = number;
        this.cvv = cvv;
        this.dueDate = dueDate;
        this.client = client;
    }

    public Card(String holderName, String number, Integer cvv, LocalDate dueDate, Client client) {
        this.holderName = holderName;
        this.number = number;
        this.cvv = cvv;
        this.dueDate = dueDate;
        this.client = client;
    }

    public Card() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate.withDayOfMonth(1);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
    
}
