package com.sdm.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import com.sdm.model.Produto;
import com.sdm.model.Categoria;
import com.sdm.model.Movimentacao;
import java.util.List;

public interface RemoteEstoque extends Remote {

    // Produtos
    ArrayList<Produto> listarProdutos() throws RemoteException;
    Produto buscarProdutoPorId(int id) throws RemoteException;
    boolean inserirProduto(Produto p) throws RemoteException;
    boolean atualizarProduto(Produto p) throws RemoteException;
    boolean excluirProduto(int id) throws RemoteException;

    // Categorias
    List<Categoria> listarCategorias() throws RemoteException;
    Categoria buscarCategoriaPorId(int id) throws RemoteException;
    void inserirCategoria(Categoria c) throws RemoteException;
    boolean atualizarCategoria(Categoria c) throws RemoteException;
    boolean excluirCategoria(int id) throws RemoteException;

    // Movimentações
    ArrayList<Movimentacao> listarMovimentacoes() throws RemoteException;
    boolean registrarMovimentacao(Movimentacao m) throws RemoteException;
}