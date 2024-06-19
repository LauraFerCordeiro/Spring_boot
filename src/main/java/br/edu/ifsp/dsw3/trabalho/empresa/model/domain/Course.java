package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false, columnDefinition = "DECIMAL")
    private BigDecimal value;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "end_date", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate endDate;

    @OneToMany(mappedBy = "department")
    @JsonBackReference
    private List<Client> clients;

    public Course(Long id, BigDecimal value, String name, String description, LocalDate endDate,
            List<Client> clients) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.description = description;
        this.endDate = endDate;
        this.clients = clients;
    }

    public Course(BigDecimal value, String name, String description, LocalDate endDate, List<Client> clients) {
        this.value = value;
        this.name = name;
        this.description = description;
        this.endDate = endDate;
        this.clients = clients;
    }

    public Course() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getendDate() {
        return endDate;
    }

    public void setendDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

}
