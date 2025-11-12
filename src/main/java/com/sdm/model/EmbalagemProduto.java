package com.sdm.model;

public enum EmbalagemProduto {
    PLASTICO, VIDRO, LATA;

    @Override
    public String toString() {
        switch (this) {
            case PLASTICO:
                return "Plástico";
            case VIDRO:
                return "Vidro";
            case LATA:
                return "Lata";
            default:
                return super.toString();
        }
    }
}