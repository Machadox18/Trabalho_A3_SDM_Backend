package com.sdm.model;

public enum EmbalagemProduto {
    LATA,
    VIDRO,
    PLASTICO;

    @Override
    public String toString() {
        switch (this) {
            case LATA: return "Lata";
            case VIDRO: return "Vidro";
            case PLASTICO: return "Plástico";
            default: return super.toString();
        }
    }

    public static EmbalagemProduto fromString(String text) {
        if (text == null) return null;

        for (EmbalagemProduto e : EmbalagemProduto.values()) {
            // Compara com a versão "bonita" (toString)
            if (e.toString().equalsIgnoreCase(text)) {
                return e;
            }

            // Compara com o nome do enum original (LATA, VIDRO...)
            if (e.name().equalsIgnoreCase(text)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Embalagem inválida: " + text);
    }
}
