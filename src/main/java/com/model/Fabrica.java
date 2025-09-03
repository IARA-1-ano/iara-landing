package com.model;

// tabela: fabrica
public class Fabrica {
  // Atributos
  private int id; // coluna: id
  private String nomeUnidade; // coluna: nome
  private String cnpj; // coluna: cnpj_unidade
  private boolean status; // coluna: status
  private String email; // coluna: email_corporativo
  private String nomeEmpresa; // coluna: nome_industria
  private String ramo; // coluna: ramo

  // Construtores
  public Fabrica(int id, String nomeUnidade, String cnpj, boolean status, String email, String nomeEmpresa,
      String ramo) {
    this.id = id;
    this.nomeUnidade = nomeUnidade;
    this.cnpj = cnpj;
    this.status = status;
    this.email = email;
    this.nomeEmpresa = nomeEmpresa;
    this.ramo = ramo;
  }

  public Fabrica() {
  }

  // Getters
  public int getId() {
    return id;
  }
  
  public String getNomeUnidade() {
    return nomeUnidade;
  }
  
  public String getCnpj() {
    return cnpj;
  }
  
  public boolean getStatus() {
    return status;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getNomeEmpresa() {
    return nomeEmpresa;
  }
  
  public String getRamo() {
    return ramo;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setNomeUnidade(String name) {
    this.nomeUnidade = name;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setNomeEmpresa(String factory_name) {
    this.nomeEmpresa = factory_name;
  }

  public void setRamo(String sector) {
    this.ramo = sector;
  }
}
