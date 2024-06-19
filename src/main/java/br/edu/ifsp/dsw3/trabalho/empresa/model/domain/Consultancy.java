package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultancies")
public class Consultancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "value", nullable = false, columnDefinition = "DECIMAL")
    private BigDecimal value;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate data;

    @Column(nullable = false)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultancy_id_fk")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultancy_id_fk")
    private Worker worker;

    public Consultancy(Long id, BigDecimal value, LocalDate data, String description, Client client, Worker worker) {
        this.id = id;
        this.value = value;
        this.data = data;
        this.description = description;
        this.client = client;
        this.worker = worker;
    }

    public Consultancy(BigDecimal value, LocalDate data, String description, Client client, Worker worker) {
        this.value = value;
        this.data = data;
        this.description = description;
        this.client = client;
        this.worker = worker;
    }

    public Consultancy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    
}
