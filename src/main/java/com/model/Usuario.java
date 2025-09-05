package com.model;

import java.time.LocalDate;

// tabela: usuario
public class Usuario {
  // Atributos
  private int id; //coluna: id
  private String nome; // coluna: nome
  private String email; // coluna: email
  private String senha; // coluna: senha
  private NivelAcesso nivelAcesso; //coluna: nivel_acesso
  private LocalDate dtCriacao; // coluna: dt_criacao
  private boolean status; //coluna: status
  private int fkFabrica;

  // Construtores
  public Usuario(int id, String nome, String email, String senha, NivelAcesso nivelAcesso, LocalDate dtCriacao,
      boolean status, int fkFabrica) {

    this.id = id;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.nivelAcesso = nivelAcesso;
    this.dtCriacao = dtCriacao;
    this.status = status;
    this.fkFabrica = fkFabrica;
  }

  public Usuario() {
    super();
  }

  // Getters
  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getSenha() {
    return senha;
  }

  public NivelAcesso getNivelAcesso() {
    return nivelAcesso;
  }

  public LocalDate getDtCriacao() {
    return dtCriacao;
  }

  public boolean getStatus() {
    return status;
  }

  public int getFkFabrica() {
    return fkFabrica;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSenha(String password) {
    this.senha = password;
  }

  public void setNivelAcesso(NivelAcesso accessLevel) {
    this.nivelAcesso = accessLevel;
  }

  public void setDtCriacao(LocalDate creationDate) {
    this.dtCriacao = creationDate;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public void setFkFabrica(int fkFabrica) {
    this.fkFabrica = fkFabrica;
  }
}
