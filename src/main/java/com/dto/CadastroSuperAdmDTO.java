package com.dto;

public class CadastroSuperAdmDTO {
  private String nome;
  private String cargo;
  private String email;
  private String senha;

  public CadastroSuperAdmDTO(String nome, String cargo, String email, String senha) {
    this.nome = nome;
    this.cargo = cargo;
    this.email = email;
    this.senha = senha;
  }

  // Getters
  public String getNome() {
    return nome;
  }

  public String getCargo() {
    return cargo;
  }

  public String getEmail() {
    return email;
  }

  public String getSenha() {
    return senha;
  }

  // Setters
  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}
