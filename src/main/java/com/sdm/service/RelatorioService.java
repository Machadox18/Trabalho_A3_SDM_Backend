package com.sdm.service;

import java.util.List;

import com.sdm.dao.ProdutoDAO;
import com.sdm.dao.CategoriaDAO;

import com.sdm.model.Produto;
import com.sdm.model.Categoria;

public class RelatorioService {
    private ProdutoDAO dao = new ProdutoDAO();
    private CategoriaDAO catDAO = new CategoriaDAO();

    public List<Produto> listarPrecos() {
        return dao.listarPrecos();
    }

    public List<Produto> listarBalancoEstoque() {
        return dao.listarBalancoEstoque();
    }

    public List<Produto> listarAbaixoMinimo() {
        return dao.listarAbaixoMinimo();
    }

    public List<Categoria> produtoPorCategoria() {
        return catDAO.produtoPorCategoria();
    }
    
}
