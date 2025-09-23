package com.model;

public class Plano {
  private Integer id;
  private String nome;
  private double valor;
  private String descricao;

  // Construtor
  public Plano(Integer id, String nome, double valor, String descricao) {
    this.id = id;
    this.nome = nome;
    this.valor = valor;
    this.descricao = descricao;
  }

  // toString
  public String toString() {
    return String.format("ID: %d\nNome: %s\nValor: R$%.2f\nDescrição: %s\n", this.id, this.nome, this.valor, this.descricao);
  }

  // Getters e Setters
  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
}
