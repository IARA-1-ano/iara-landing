package com.exception;

public class ExcecaoDePagina extends RuntimeException {
  private final String metodoCausador;

  public ExcecaoDePagina(String message, String metodoCausador) {
    super(message);
    this.metodoCausador = metodoCausador;
  }

  // Métodos de fábrica para padronização da mensagem
  public static ExcecaoDePagina campoUnicoDuplicado(String entidade, String campo, boolean subsFem, String metodoCausador) {
    String msg = "Já existe %s %s %s com esse %s".formatted(subsFem ? "uma" : "um", entidade, subsFem ? "registrada" : "registrado", campo);
    return new ExcecaoDePagina(msg, metodoCausador);
  }

  public static ExcecaoDePagina cnpjDuplicado(String metodoCausador) {
    return campoUnicoDuplicado("fábrica", "cnpj", true, metodoCausador);
  }

  public static ExcecaoDePagina cpfDuplicado(String entidade, String metodoCausador) {
    return campoUnicoDuplicado(entidade, "cpf", false, metodoCausador);
  }

  public static ExcecaoDePagina emailDuplicado(String entidade, String metodoCausador) {
    return campoUnicoDuplicado(entidade, "email", false, metodoCausador);
  }

  public static ExcecaoDePagina nomeDuplicado(String metodoCausador) {
    return campoUnicoDuplicado("plano", "nome", false, metodoCausador);
  }

  public static ExcecaoDePagina senhaCurta(int tamanhoMinimo, String metodoCausador) {
    String msg = "A senha deve ter pelo menos %d dígitos".formatted(tamanhoMinimo);
    return new ExcecaoDePagina(msg, metodoCausador);
  }

  public static ExcecaoDePagina campoNecessarioFaltante(String campo, String metodoCausador) {
    String msg = "Preencha o campo %s antes de enviar".formatted(campo);
    return new ExcecaoDePagina(msg, metodoCausador);
  }

  // Getters
  public String getMetodoCausador() {
    return metodoCausador;
  }
}
