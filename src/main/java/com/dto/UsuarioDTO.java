package com.dto;

import com.model.TipoAcesso;

import java.time.LocalDate;

public class UsuarioDTO {
  // Atributos
  private Integer id;
  private String nome;
  private String email;
  private TipoAcesso tipoAcesso;
  private LocalDate dataCriacao;
  private Boolean status;
  private Integer idFabrica;

  // Construtor
  public UsuarioDTO(Integer id, String nome, String email, TipoAcesso tipoAcesso, LocalDate dataCriacao, Boolean status, Integer idFabrica) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.tipoAcesso = tipoAcesso;
    this.dataCriacao = dataCriacao;
    this.status = status;
    this.idFabrica = idFabrica;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public TipoAcesso getTipoAcesso() {
    return tipoAcesso;
  }

  public void setTipoAcesso(TipoAcesso tipoAcesso) {
    this.tipoAcesso = tipoAcesso;
  }

  public LocalDate getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDate dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
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
    return "UsuarioDTO{id=%d, nome='%s', email='%s', tipoAcesso=%s, dataCriacao=%s, status=%b, idFabrica=%d}"
        .formatted(id, nome, email, tipoAcesso, dataCriacao, status, idFabrica);
  }
}
