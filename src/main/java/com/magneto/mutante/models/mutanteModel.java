package com.magneto.mutante.models;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "mutante")
public class mutanteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    private String adn;
    private String mutante;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAdn() {
        return adn;
    }
    public void setAdn(String adn) {
        this.adn = adn;
    }
    public String getMutante() {
        return mutante;
    }
    public void setMutante(String mutante) {
        this.mutante = mutante;
    }    
}
