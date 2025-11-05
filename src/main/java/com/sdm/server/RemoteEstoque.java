package com.sdm.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.sdm.model.Produto;
import com.sdm.model.Categoria;
import com.sdm.model.Movimentacao;

/**
 * Interface remota do sistema de estoque.
 * Define os métodos acessíveis pelo cliente via RMI.
 */
public interface RemoteEstoque extends Remote {

    /* ========================= PRODUTOS ========================= */

    List<Produto> listarProdutos() throws RemoteException;
    Produto buscarProdutoPorId(int id) throws RemoteException;
    void inserirProduto(Produto p) throws RemoteException;
    void atualizarProduto(Produto p) throws RemoteException;
    boolean excluirProduto(int id) throws RemoteException;

    /* ========================= CATEGORIAS ========================= */

    List<Categoria> listarCategorias() throws RemoteException;
    Categoria buscarCategoriaPorId(int id) throws RemoteException;
    void inserirCategoria(Categoria c) throws RemoteException;
    boolean atualizarCategoria(Categoria c) throws RemoteException;
    boolean excluirCategoria(int id) throws RemoteException;

    /* ========================= MOVIMENTAÇÕES ========================= */

    boolean registrarMovimentacao(Movimentacao m) throws RemoteException;

    /* ========================= RELATÓRIOS ========================= */

    List<Produto> relatorioProdutosAbaixoMinimo() throws RemoteException;
    List<Produto> relatorioProdutosAcimaMaximo() throws RemoteException;
    Map<String, Object> relatorioBalanco() throws RemoteException;

}
