package com.italoghiele.projetointegrador.domain.enums;

public enum Language {

    PORTUGUESE(1, "Portugês"),
    ENGLISH(2, "Inglês"),
    SPANISH(3, "Espanhol");

    private int cod;
    private String language;

    Language(int cod, String language) {
        this.cod = cod;
        this.language = language;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return language;
    }

    public static Language toEnum(Integer cod){
        if(cod == null) {
            return null;
        }

        for(Language x : Language.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
