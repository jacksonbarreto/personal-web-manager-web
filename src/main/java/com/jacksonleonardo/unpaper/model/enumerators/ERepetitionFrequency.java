package com.jacksonleonardo.unpaper.model.enumerators;

import java.io.Serializable;

public enum ERepetitionFrequency implements Serializable {
    WEEKLY(1, "Semanal"),
    FORTNIGHTLY(2, "Quinzenal"),
    MONTHLY(3, "Mensal"),
    QUARTERLY(4, "Trimestral"),
    YEARLY(5, "Anual"),
    NONE(6, "Nenhum");

    private final Integer ID;
    private final String name;

    ERepetitionFrequency(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
