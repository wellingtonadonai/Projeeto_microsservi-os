package br.com.microsservicos.entity;


import java.util.UUID;


public class Produto {

    private UUID id = UUID.randomUUID();
    private String nome;
    private Double valor;

    public Produto(UUID id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }
    public Produto(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
