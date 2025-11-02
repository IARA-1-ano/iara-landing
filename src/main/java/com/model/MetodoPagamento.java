package com.model;

import com.utils.StringUtils;

/*
* ENUM referente a tabela metodo_pagamento, onde cada instância é uma simplificação do nome presente no banco, e cada atributo é referente a uma coluna do banco de dados:
*
*  metodo -> tipo_pagamento
* */
public enum MetodoPagamento {

    CREDITO(1, "Cartão de Crédito"),
    BOLETO(2, "Boleto Bancário"),
    PIX(3, "PIX"),
    TRANSFERENCIA(4, "Transferência Bancária"),
    DEBITO(5, "Débito Automático");

    private final int id;
    private final String metodo;

    MetodoPagamento(int id, String metodo){
        this.id = id;
        this.metodo = metodo;
    }

    // Método que recebe como parâmetro um número e retorna a instância que tem nível compatível com o número recebido
    public static MetodoPagamento deId(int nivel){
        for (MetodoPagamento m : values()){
            if (m.id == nivel){
                return m;
            }
        }

        throw new IllegalArgumentException("Nível Inválido");
    }

    public int getId(){
        return id;
    }

    public String getMetodo(){
        return metodo;
    }

    public String toString() {
        return StringUtils.capitalize(name());
    }

}
