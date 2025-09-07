package com.controller;

public enum StatusResposta {
  ERRO_INTERNO("Erro interno inexperado"),
  ACESSO_NEGADO("Acesso negado"),
  OK("Ok");

  private final String mensagem;

  StatusResposta(String mensagem) {
    this.mensagem = mensagem;
  }

  @Override
  public String toString() {
    return mensagem;
  }
}
