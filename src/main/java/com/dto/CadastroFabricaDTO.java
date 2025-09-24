package com.dto;

public class CadastroFabricaDTO {
    private String nome;
    private String cnpj;
    private String email;
    private String nomeEmpresa;
    private String ramo;
    private int fkEndereco;
    private int fkPlano;

    public CadastroFabricaDTO(String nome, String cnpj, String email, String nomeEmpresa, String ramo, int fkEndereco, int fkPlano) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.nomeEmpresa = nomeEmpresa;
        this.ramo = ramo;
        this.fkEndereco = fkEndereco;
        this.fkPlano = fkPlano;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String factory_name) {
        this.nomeEmpresa = factory_name;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String sector) {
        this.ramo = sector;
    }

    public int getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(int fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public int getFkPlano() {
        return fkPlano;
    }

    public void setFkPlano(int fkPlano) {
        this.fkPlano = fkPlano;
    }
}
