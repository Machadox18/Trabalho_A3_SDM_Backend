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
    Método para atualizar um produto no bando de dados
     */
    public void atualizar(Produto p) {
        produtoDAO.atualizar(p);
    }
    
    /*
    Método para buscar um produto no banco de dados baseado em seu id
     */
    public Produto buscarPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }

    /*
    Método para deletar um produto do banco de dados
     */
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
