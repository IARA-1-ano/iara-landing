package com.dto;

public class LoginDTO {
  // Atributos
  private String email;
  private String senha;

  // Construtor
  public LoginDTO(String email, String senha) {
    this.email = email;
    this.senha = senha;
  }

  // Getters e Setters
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  // toString
  @Override
  public String toString() {
    return "LoginDTO{email='%s', senha='%s'}".formatted(email, senha);
  }
}
