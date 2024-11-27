package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

public enum Role {
    ADMIN("ADMIN"),
    PERSON("PERSON"),
    COMPANY("COMPANY");

    private String descricao;

    Role (String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
