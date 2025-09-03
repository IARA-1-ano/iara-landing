package com.model;

import java.time.LocalDate;

// TODO: incluir atributo para a coluna fk_fabrica: int

// tabela: usuario
public class Usuario {
  // Atributos
  private int id; // coluna: id 
  private String nome; // coluna: nome
  private String login; // coluna: login
  private String senha; // coluna: senha
  private NivelAcesso nivelAcesso; // coluna: nivel_acesso
  private LocalDate dtCriacao; // coluna: data_criacao
  private boolean status; // coluna: status

  // Construtores
  public Usuario(int id, String nome, String login, String senha, NivelAcesso nivelAcesso, LocalDate dtCriacao,
      boolean status) {
    this.id = id;
    this.nivelAcesso = nivelAcesso;
    this.dtCriacao = dtCriacao;
    this.login = login;
    this.nome = nome;
    this.senha = senha;
    this.status = status;
  }

  public Usuario() {
  }

  // Getters
  public int getId() {
    return id;
  }
  
  public String getNome() {
    return nome;
  }

  public String getLogin() {
    return login;
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

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setNome(String name) {
    this.nome = name;
  }

  public void setLogin(String login) {
    this.login = login;
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
}
