package com.dto;

public class EnderecoDTO {
    //Atributos
    private String cep;
    private int numero;
    private String rua;
    private String complemento;

    //Construtor

    public EnderecoDTO(String cep, int numero, String rua, String complemento) {
        this.cep = cep;
        this.numero = numero;
        this.rua = rua;
        this.complemento = complemento;
    }

    //toString
    public String toString(){
        return String.format("CEP: %s\nRua: %s\nNúmero: %d\nComplemento: %s\n", this.cep, this.rua
        , this.numero, this.complemento);
    }

    //Getters

    public String getCep() {
        return cep;
    }

    public int getNumero() {
        return numero;
    }

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }

    //Setters

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
