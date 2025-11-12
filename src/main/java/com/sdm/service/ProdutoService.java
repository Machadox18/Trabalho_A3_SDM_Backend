package com.sdm.service;

import com.sdm.dao.ProdutoDAO;
import com.sdm.model.Produto;

public class ProdutoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public void inserir(Produto p) {
        produtoDAO.inserir(p);
    }

    /*
    Método para reajustar o preço dos produtos em um determinado percentual
     */
    public boolean reajustarPrecos(double percentual) {
        return produtoDAO.reajustarPrecos(percentual);
    }
}
