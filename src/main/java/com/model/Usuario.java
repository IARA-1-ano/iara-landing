package com.model;

import java.time.LocalDate;

// tabela: usuario
public class Usuario extends AbstractModel {
  // Atributos
  private String login;
  private String senha;
  private NivelAcesso nivelAcesso;
  private LocalDate dtCriacao;
  private boolean status;
  private int fkFabrica;

  // Construtores
  public Usuario(int id, String nome, String login, String senha, NivelAcesso nivelAcesso, LocalDate dtCriacao,
      boolean status, int fkFabrica) {
    super(id, nome);

    this.nivelAcesso = nivelAcesso;
    this.dtCriacao = dtCriacao;
    this.login = login;
    this.senha = senha;
    this.status = status;
    this.fkFabrica = fkFabrica;
  }

  public Usuario() {
    super();
  }

  // Getters
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

  public int getFkFabrica() {
    return fkFabrica;
  }

  // Setters
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

  public void setFkFabrica(int fkFabrica) {
    this.fkFabrica = fkFabrica;
  }
}
