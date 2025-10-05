package com.dto;

public class CadastroUsuarioDTO {
  // Atributos
  private String nome;
  private String email;
  private String senha;
  private Integer idFabrica;

  // Construtor
  public CadastroUsuarioDTO(String nome, String email, String senha, Integer idFabrica) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.idFabrica = idFabrica;
  }

  // Getters e Setters
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

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

  public Integer getIdFabrica() {
    return idFabrica;
  }

  public void setIdFabrica(Integer idFabrica) {
    this.idFabrica = idFabrica;
  }

  // toString
  @Override
  public String toString() {
    return "CadastroUsuarioDTO{nome='%s', email='%s', senha='%s', idFabrica=%d}".formatted(nome, email, senha, idFabrica);
  }
}
