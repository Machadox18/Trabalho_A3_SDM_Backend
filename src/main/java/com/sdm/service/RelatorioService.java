package com.sdm.service;

import java.util.List;

import com.sdm.dao.ProdutoDAO;
import com.sdm.model.Produto;

public class RelatorioService {
    private ProdutoDAO dao = new ProdutoDAO();

    public List<Produto> listarPrecos() {
        return dao.listarPrecos();
    }

    public List<Produto> listarBalancoEstoque() {
        return dao.listarBalancoEstoque();
    }

}
