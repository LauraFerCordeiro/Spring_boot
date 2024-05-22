package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;

public class Departament implements Serializable{
    private Long id;
    private String address;
    private String name;
    private String description;
    private Worker worker;
    
    public Departament(Long id, String address, String name, String description, Worker worker) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.description = description;
        this.worker = worker;
    }

    public Departament(String address, String name, String description, Worker worker) {
        this.address = address;
        this.name = name;
        this.description = description;
        this.worker = worker;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    
}
