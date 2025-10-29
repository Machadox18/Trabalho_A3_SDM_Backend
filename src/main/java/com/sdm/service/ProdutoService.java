package com.sdm.service;

import com.sdm.dao.ProdutoDAO;

public class ProdutoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    /*
    Método para reajustar o preço dos produtos em um determinado percentual
     */
    public boolean reajustarPrecos(double percentual) {
        return produtoDAO.reajustarPrecos(percentual);
    }
}
