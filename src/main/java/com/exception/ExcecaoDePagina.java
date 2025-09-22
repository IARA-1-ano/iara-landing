package com.exception;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ExcecaoDePagina extends RuntimeException {
  private String destino;

  public ExcecaoDePagina(String message, String destino) {
    super(message);
    this.destino = destino;
  }

  // Métodos de fábrica para padronização da mensagem
  public static ExcecaoDePagina campoUnicoDuplicado(String entidade, String campo, boolean subsFem, String destino) {
    String msg = "Já existe %s %s %s com esse %s".formatted(subsFem ? "uma" : "um", entidade, subsFem ? "registrado" : "registrada", campo);
    return new ExcecaoDePagina(msg, destino);
  }

  public static ExcecaoDePagina cnpjDuplicado(String destino) {
    return campoUnicoDuplicado("fábrica", "cnpj", true, destino);
  }

  public static ExcecaoDePagina cpfDuplicado(String entidade, String destino) {
    return campoUnicoDuplicado(entidade, "cpf", false, destino);
  }

  public static ExcecaoDePagina emailDuplicado(String entidade, String destino) {
    return campoUnicoDuplicado(entidade, "email", false, destino);
  }

  public static ExcecaoDePagina senhaCurta(int tamanhoMinimo, String destino) {
    String msg = "A senha deve ter pelo menos %d dígitos".formatted(tamanhoMinimo);
    return new ExcecaoDePagina(msg, destino);
  }

  // Getters
  public String getDestino() {
    return destino;
  }

  public String getMensagemUrl() {
    return URLEncoder.encode(getMessage(), StandardCharsets.UTF_8);
  }

  // Setters
  public void setDestino(String destino) {
    this.destino = destino;
  }
}
