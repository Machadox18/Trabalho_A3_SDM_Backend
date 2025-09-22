package com.sdm.model;

import java.time.LocalDateTime;

public class Movimentacao {
    private int id;
    private int produtoId;
    private int quantidade;
    private String tipo; // ENTRADA ou SAIDA
    private LocalDateTime data;

    public Movimentacao() {
        this.data = LocalDateTime.now();
    }

    public Movimentacao(int produtoId, int quantidade, String tipo, LocalDateTime data) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.data = data;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
}
