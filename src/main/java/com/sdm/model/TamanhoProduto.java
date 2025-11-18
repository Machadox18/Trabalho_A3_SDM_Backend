package com.sdm.model;

public enum TamanhoProduto {

    PEQUENO("Pequeno"),
    MEDIO("Médio"),
    GRANDE("Grande");

    private final String label;

    TamanhoProduto(String label) {
        this.label = label;
    }

    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }

    /**
     * Converte uma string qualquer para o enum de forma tolerante:
     * - compara com label (Pequeno / Médio / Grande)
     * - se não encontrar, tenta com valueOf (PEQUENO / MEDIO / GRANDE)
     * - faz normalização (trim, maiúsculas, remove acentos básicos)
     */
    public static TamanhoProduto fromString(String text) {
        if (text == null) return null;
        String t = text.trim();

        // 1) tenta comparar com label (case-insensitive)
        for (TamanhoProduto tp : values()) {
            if (tp.label.equalsIgnoreCase(t)) return tp;
        }

        // 2) normaliza e tenta valueOf (remove acentos e espaços)
        String normalized = removeAccents(t).toUpperCase().replaceAll("\\s+", "_");
        try {
            return TamanhoProduto.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            // 3) fallback: tenta matching por prefixo (ex: "P" -> PEQUENO)
            for (TamanhoProduto tp : values()) {
                if (tp.name().startsWith(normalized)) return tp;
            }
        }

        // nada funcionou
        throw new IllegalArgumentException("Tamanho inválido: " + text);
    }

    // utilitário simples para remover acentos (não precisa lib externa)
    private static String removeAccents(String s) {
        String[][] replacements = {
                {"Á","A"}, {"À","A"}, {"Â","A"}, {"Ã","A"}, {"Ä","A"},
                {"á","a"}, {"à","a"}, {"â","a"}, {"ã","a"}, {"ä","a"},
                {"É","E"}, {"È","E"}, {"Ê","E"}, {"Ë","E"},
                {"é","e"}, {"è","e"}, {"ê","e"}, {"ë","e"},
                {"Í","I"}, {"Ì","I"}, {"Î","I"}, {"Ï","I"},
                {"í","i"}, {"ì","i"}, {"î","i"}, {"ï","i"},
                {"Ó","O"}, {"Ò","O"}, {"Ô","O"}, {"Õ","O"}, {"Ö","O"},
                {"ó","o"}, {"ò","o"}, {"ô","o"}, {"õ","o"}, {"ö","o"},
                {"Ú","U"}, {"Ù","U"}, {"Û","U"}, {"Ü","U"},
                {"ú","u"}, {"ù","u"}, {"û","u"}, {"ü","u"},
                {"Ç","C"}, {"ç","c"}
        };
        String out = s;
        for (String[] r : replacements) out = out.replace(r[0], r[1]);
        return out;
    }
}
