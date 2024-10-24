package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String address_name;
    private String district;
    private String city;
    private String state;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Endereco(){}

    public Endereco(String cep, String address_name, String district, String city, String state, Usuario usuario) {
        this.cep = cep;
        this.address_name = address_name;
        this.district = district;
        this.city = city;
        this.state = state;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getAddress_name() {
        return address_name;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
