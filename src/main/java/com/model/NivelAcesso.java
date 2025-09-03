package com.model;

// Enum para representar e tornar semântico os níveis de acesso do banco
public enum NivelAcesso {
  USUARIO(0),
  ADMIN(1),
  SUPER_ADMIN(2);

  private final int nivel;

  NivelAcesso(int nivel) {
    this.nivel = nivel;
  }

  public int nivel() {
    return nivel;
  }
}
