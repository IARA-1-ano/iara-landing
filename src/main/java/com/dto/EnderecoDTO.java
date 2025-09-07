package com.dto;

import com.model.Endereco;

public class EnderecoDTO extends Endereco {
    //Construtor
    public EnderecoDTO(String cep, int numero, String rua, String complemento){
        super(null, cep, numero, rua, complemento);
    }

    //Cancelando métodos com ID
    public Integer getId(){
        throw new UnsupportedOperationException();
    }

    public void setId(Integer id){
        throw new UnsupportedOperationException();
    }
}
