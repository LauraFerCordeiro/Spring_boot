package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Departaments")
public class Departament implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    // O Salina falou que deveria ter uma lista de workers aqui
    // Eu tinha colocado mas tirei por 2 motivos:
    // 1 - No banco de dados não tava aparecendo
    // 2 - Não tava dando pra cadastrar Departamento sem a lista de workers, e não dá pra cadastrar um worker sem um departamento

    public Departament(Long id, String address, String name, String description) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.description = description;
    }

    public Departament(String address, String name, String description) {
        this.address = address;
        this.name = name;
        this.description = description;
    }

    public Departament() {
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
    
}
