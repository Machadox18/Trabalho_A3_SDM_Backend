package com.sdm.model;

import java.io.Serializable;

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;

    // Atributo privado que armazena o nome da categoria.
    private String nome;

    // Atributo privado que representa o tamanho do produto (provavelmente um enum ou outra classe).
    private TamanhoProduto tamanho;

    // Atributo privado que representa a embalagem do produto (também pode ser enum ou classe).
    private EmbalagemProduto embalagem;

    private int qtd_produtoPorCategoria;

    // Construtor vazio (padrão), necessário para alguns frameworks ou para criar objetos sem inicializar os campos.
    public Categoria() {
    }

    // Construtor com parâmetros para inicializar todos os atributos da classe.
    public Categoria(int id, String nome, TamanhoProduto tamanho, EmbalagemProduto embalagem) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    public Categoria(int idCategoria, String nomeCategoria) {
        this.id = idCategoria;
        this.nome = nomeCategoria;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, int qtd_produtoPorCategoria) {
        this.nome = nome;
        this.qtd_produtoPorCategoria = qtd_produtoPorCategoria;
    }

    // Método getter que retorna o valor do atributo 'id'.
    public int getId() {
        return id;
    }

    // Método getter que retorna o valor do atributo 'nome'.
    public String getNome() {
        return nome;
    }

    // Método setter que altera o valor do atributo 'id'.
    public void setId(int id) {
        this.id = id;
    }

    // Método setter que altera o valor do atributo 'nome'.
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o atributo 'tamanho'.
    public TamanhoProduto getTamanho() {
        return tamanho;
    }

    // Setter para o atributo 'tamanho'.
    public void setTamanho(TamanhoProduto tamanho) {
        this.tamanho = tamanho;
    }

    // Getter para o atributo 'embalagem'.
    public EmbalagemProduto getEmbalagem() {
        return embalagem;
    }

    // Setter para o atributo 'embalagem'.
    public void setEmbalagem(EmbalagemProduto embalagem) {
        this.embalagem = embalagem;
    }

    public int getQtd_produtoPorCategoria() {
        return qtd_produtoPorCategoria;
    }

    public void setQtd_produtoPorCategoria(int qtd_produtoPorCategoria) {
        this.qtd_produtoPorCategoria = qtd_produtoPorCategoria;
    }

    // Método sobrescrito que retorna uma representação em texto (string) do objeto Categoria.
    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
