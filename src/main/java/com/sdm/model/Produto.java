package com.sdm.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private double precoUnitario;
    private double precoTotalProduto;
    private double precoTotalEstoque;
    private String unidade;
    private int quantidadeEstoque;
    private int quantidadeMinima;
    private int quantidadeMaxima;
    private int totalSaidas;
    private int totalEntradas;
    private Categoria categoria;

    //Construtores

    public Produto() {
    }

    public Produto(String nome, int totalSaidas, int totalEntradas) {
        this.nome = nome;
        this.totalSaidas = totalSaidas;
        this.totalEntradas = totalEntradas;
    }

    public Produto(int id, String nome, double precoUnitario, String unidade, int quantidadeEstoque, int quantidadeMinima, int quantidadeMaxima, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeMaxima = quantidadeMaxima;
        this.categoria = categoria;
    }

    public Produto(String nome, double precoUnitario, String unidade) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    public Produto(double precoTotalEstoque) {
        this.precoTotalEstoque = precoTotalEstoque;
    }

    public Produto(String nome, int quantidadeEstoque, double precoTotalProduto) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoTotalProduto = precoTotalProduto;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getPrecoTotalProduto() {
        return precoTotalProduto;
    }

    public void setPrecoTotalProduto(double precoTotalProduto) {
        this.precoTotalProduto = precoTotalProduto;
    }

    public double getPrecoTotalEstoque() {
        return precoTotalEstoque;
    }

    public void setPrecoTotalEstoque(double precoTotalEstoque) {
        this.precoTotalEstoque = precoTotalEstoque;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public int getTotalSaidas() {
        return totalSaidas;
    }

    public void setTotalSaidas(int totalSaidas) {
        this.totalSaidas = totalSaidas;
    }

    public int getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(int totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Método para imprimir o produto como texto
    public String toString() {
        return "Id:" + id + "Nome:" + nome + "Preço por unidade" + precoUnitario + unidade;
    }
}
