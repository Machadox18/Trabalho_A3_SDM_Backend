package com.sdm.service;

import com.sdm.dao.ProdutoDAO;
import com.sdm.model.Produto;
import java.util.ArrayList;

public class ProdutoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    
    /*
    Método para inserir produtos no banco de dados
     */
    public void inserir(Produto p) {
        produtoDAO.inserir(p);
    }
    
    /*
    Método para listar as caracteristicas de um produto
     */
    public ArrayList<Produto> listar() {
        return produtoDAO.listar();
    }

    /*
    Método para reajustar o preço dos produtos em um determinado percentual
     */
    public boolean reajustarPrecos(double percentual) {
        return produtoDAO.reajustarPrecos(percentual);
    }
}
