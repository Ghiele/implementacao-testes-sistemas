package com.italoghiele.projetointegrador.domain.enums;

public enum State {

    ACRE(12, "AC", "Acre"),
    ALAGOAS(27, "AL", "Alagoas"),
    AMAPA(16, "AP", "Amapá"),
    AMAZONAS(13, "AM", "Amazonas"),
    BAHIA(29, "BA", "Bahia"),
    CEARA(23, "RN", "Ceará"),
    DISTRITOFEDERAL(53, "DF", "Distrito Federal"),
    ESPIRITOSANTO(32, "ES", "Espírito Santo"),
    GOIAS(52, "GO", "Goiás"),
    MARANHAO(21, "MA", "Maranhão"),
    MATOGROSSO(51, "MT", "Mato Grosso"),
    MATOGROSSODOSUL(50, "MS", "Mato Grosso do Sul"),
    MINAGERAIS(31, "MG", "Minas Gerais"),
    PARA(15, "PA", "Pará"),
    PARAIBA(25, "PB", "Paraíba"),
    PARANA(41, "PR", "Paraná"),
    PERNAMBUCO(26, "PE", "Pernambuco"),
    PIAUI(22, "PI", "Piauí"),
    RIODEJANEIRO(33, "RJ", "Rio de Janeiro"),
    RIOGRANDEDONORTE(24, "AC", "Rio Grande do Norte"),
    RIOGRANDEDOSUL(43, "RS", "Rio Grande do Sul"),
    RONDONIA(11, "RO", "Rondônia"),
    RORAIMA(14, "RR", "Roraima"),
    SANTACATARINA(42, "SC", "Santa Catarina"),
    SAOPAULO(35, "SP", "São Paulo"),
    SERGIPE(28, "SE", "Sergipe"),
    TOCANTINS(17, "TO", "Tocantins");

    private Long ibgeId;

    private String initials;

    private String name;

    State(long ibgeId, String initials, String name) {
        this.ibgeId = ibgeId;
        this.initials = initials;
        this.name = name;
    }

    public Long getIbgeId() {
        return ibgeId;
    }

    public String getInitials() {
        return initials;
    }

    public String getName() {
        return name;
    }

    public static State toEnum(Integer ibgeId){
        if(ibgeId == null) {
            return null;
        }

        for(State x : State.values()){
            if(ibgeId.equals(x.getIbgeId())){
                return x;
            }
        }

        throw new IllegalArgumentException("Código IBGE inválido: " + ibgeId);
    }

    public static State toEnum(String initials){
        if(initials == null) {
            return null;
        }

        for(State x : State.values()){
            if(initials.equals(x.getInitials())){
                return x;
            }
        }

        throw new IllegalArgumentException("Código IBGE inválido: " + initials);
    }
}
