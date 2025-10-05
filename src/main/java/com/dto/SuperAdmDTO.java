package com.dto;

public class SuperAdmDTO {
  // Atributos
  private Integer id;
  private String nome;
  private String cargo;
  private String email;

  // Construtor
  public SuperAdmDTO(Integer id, String nome, String cargo, String email) {
    this.id = id;
    this.nome = nome;
    this.cargo = cargo;
    this.email = email;
  }

  // Getters e Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // toString
  @Override
  public String toString() {
    return "SuperAdmDTO{id=%d, nome='%s', cargo='%s', email='%s'}".formatted(id, nome, cargo, email);
  }
}
