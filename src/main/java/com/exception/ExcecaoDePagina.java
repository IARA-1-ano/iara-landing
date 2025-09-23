package com.exception;

public class ExcecaoDePagina extends RuntimeException {
  public ExcecaoDePagina(String message) {
    super(message);
  }

  // Métodos de fábrica para padronização da mensagem
  public static ExcecaoDePagina campoUnicoDuplicado(String entidade, String campo, boolean subsFem) {
    String msg = "Já existe %s %s %s com esse %s".formatted(subsFem ? "uma" : "um", entidade, subsFem ? "registrada" : "registrado", campo);
    return new ExcecaoDePagina(msg);
  }

  public static ExcecaoDePagina cnpjDuplicado() {
    return campoUnicoDuplicado("fábrica", "cnpj", true);
  }

  public static ExcecaoDePagina cpfDuplicado(String entidade) {
    return campoUnicoDuplicado(entidade, "cpf", false);
  }

  public static ExcecaoDePagina emailDuplicado(String entidade) {
    return campoUnicoDuplicado(entidade, "email", false);
  }

  public static ExcecaoDePagina nomeDuplicado() {
    return campoUnicoDuplicado("plano", "nome", false);
  }

  public static ExcecaoDePagina senhaCurta(int tamanhoMinimo) {
    String msg = "A senha deve ter pelo menos %d dígitos".formatted(tamanhoMinimo);
    return new ExcecaoDePagina(msg);
  }

  public static ExcecaoDePagina campoNecessarioFaltante(String campo) {
    String msg = "Preencha o campo %s antes de enviar".formatted(campo);
    return new ExcecaoDePagina(msg);
  }

  public static ExcecaoDePagina falhaAutenticacao() {
    return new ExcecaoDePagina("Autenticação falhou. Verifique suas credenciais.");
  }
}
