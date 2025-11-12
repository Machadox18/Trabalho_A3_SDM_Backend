package com.sdm.service;

import com.sdm.dao.ProdutoDAO;
import com.sdm.model.Produto;
import java.util.ArrayList;

public class ProdutoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public void inserir(Produto p) {
        produtoDAO.inserir(p);
    }
    
    public ArrayList<Produto> listar() {
        return produtoDAO.listar();
    }
    
    public void atualizar(Produto p) {
        produtoDAO.atualizar(p);
    }
    
    public Produto buscarPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }
    
    public boolean deletar(int id) {
        return produtoDAO.deletar(id);
    }

    /*
    Método para reajustar o preço dos produtos em um determinado percentual
     */
    public boolean reajustarPrecos(double percentual) {
        return produtoDAO.reajustarPrecos(percentual);
    }
}
