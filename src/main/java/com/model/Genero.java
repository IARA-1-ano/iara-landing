package com.model;

import com.utils.StringUtils;

public enum Genero {
  MASCULINO, FEMININO, OUTROS;

  public static Genero parse(String generoStr) {
    for (Genero g : values()) {
      if (g.name().equalsIgnoreCase(generoStr)) {
        return g;
      }
    }

    return null;
  }

  public String toString() {
    return StringUtils.capitalize(name());
  }
}
