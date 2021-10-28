package com.italoghiele.projetointegrador.domain.enums;

public enum Gender {

    MALE(1, "Masculino"),
    FEMALE(2, "Feminino"),
    OTHER(3, "Outros");

    private int cod;
    private String gender;

    Gender(int cod, String gender) {
        this.cod = cod;
        this.gender = gender;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return gender;
    }

    public static Gender toEnum(Integer cod){
        if(cod == null) {
            return null;
        }

        for(Gender x : Gender.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
