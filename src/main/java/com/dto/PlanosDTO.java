package com.dto;

public class PlanosDTO {
    //Atributos
    private String nome;
    private double valor;
    private String descricao;

    //Construtor
    public PlanosDTO(String nome, double valor, String descricao){
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    //toString
    public String toString(){
        return String.format("Nome: %s\nValor: R$%.2f\nDescrição: %s\n", this.nome, this.valor, this.descricao);
    }

    //Getters

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    //Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
