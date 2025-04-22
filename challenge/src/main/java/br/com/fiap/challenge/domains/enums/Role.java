package br.com.fiap.challenge.domains.enums;

public enum Role {
    DENTISTA(1, "Dentista"),
    CLINICA(2, "Clínica");

    private int cod;
    private String descricao;

    Role(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Role toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Role x : Role.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("ID Inválido: " + cod);
    }
}
