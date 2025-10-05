package com.dto;

public class CadastroFabricaDTO {
  // Atributos
  private String nomeUnidade;
  private String cnpj;
  private String emailCorporativo;
  private String nomeIndustria;
  private String ramo;
  private Integer idPlano;

  // Construtor
  public CadastroFabricaDTO(String nomeUnidade, String cnpj, String emailCorporativo, String nomeIndustria, String ramo, Integer idPlano) {
    this.nomeUnidade = nomeUnidade;
    this.cnpj = cnpj;
    this.emailCorporativo = emailCorporativo;
    this.nomeIndustria = nomeIndustria;
    this.ramo = ramo;
    this.idPlano = idPlano;
  }

  // Getters e Setters
  public String getNomeUnidade() {
    return nomeUnidade;
  }

  public void setNomeUnidade(String nomeUnidade) {
    this.nomeUnidade = nomeUnidade;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getEmailCorporativo() {
    return emailCorporativo;
  }

  public void setEmailCorporativo(String emailCorporativo) {
    this.emailCorporativo = emailCorporativo;
  }

  public String getNomeIndustria() {
    return nomeIndustria;
  }

  public void setNomeIndustria(String nomeIndustria) {
    this.nomeIndustria = nomeIndustria;
  }

  public String getRamo() {
    return ramo;
  }

  public void setRamo(String ramo) {
    this.ramo = ramo;
  }

  public Integer getIdPlano() {
    return idPlano;
  }

  public void setIdPlano(Integer idPlano) {
    this.idPlano = idPlano;
  }

  // toString
  @Override
  public String toString() {
    return "CadastroFabricaDTO{nomeUnidade='%s', cnpj='%s', emailCorporativo='%s', nomeIndustria='%s', ramo='%s', idPlano=%d}"
        .formatted(nomeUnidade, cnpj, emailCorporativo, nomeIndustria, ramo, idPlano);
  }
}
