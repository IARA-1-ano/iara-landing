package com.dto;

import com.model.TipoAcesso;

public class AtualizacaoUsuarioDTO {
  // Atributos
  private Integer id;
  private String nome;
  private String email;
  private TipoAcesso tipoAcesso;
  private Boolean status;
  private Integer idFabrica;

  // Construtor
  public AtualizacaoUsuarioDTO(Integer id, String nome, String email, TipoAcesso tipoAcesso, Boolean status, Integer idFabrica) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.tipoAcesso = tipoAcesso;
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

  public void setTipoAcesso(TipoAcesso accessLevel) {
    this.tipoAcesso = accessLevel;
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
    return "AtualizacaoUsuarioDTO{id=%d, nome='%s', email='%s', tipoAcesso=%s, status=%b, idFabrica=%d}"
        .formatted(id, nome, email, tipoAcesso, status, idFabrica);
  }
}
