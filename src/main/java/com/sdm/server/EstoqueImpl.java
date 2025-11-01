package com.sdm.server;

import com.sdm.dao.ProdutoDAO;
import com.sdm.dao.CategoriaDAO;
import com.sdm.dao.MovimentacaoDAO;
import com.sdm.model.Produto;
import com.sdm.model.Categoria;
import com.sdm.model.Movimentacao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Implementação do serviço remoto de estoque (lado do servidor RMI).
 * Integra diretamente com os DAOs reais do sistema.
 */
public class EstoqueImpl extends UnicastRemoteObject implements RemoteEstoque {

    private final ProdutoDAO produtoDAO;
    private final CategoriaDAO categoriaDAO;
    private final MovimentacaoDAO movimentacaoDAO;

    public EstoqueImpl() throws RemoteException {
        super();
        this.produtoDAO = new ProdutoDAO();
        this.categoriaDAO = new CategoriaDAO();
        this.movimentacaoDAO = new MovimentacaoDAO();
    }

    /* ========================= PRODUTOS ========================= */

    @Override
    public List<Produto> listarProdutos() throws RemoteException {
        return produtoDAO.listar();
    }

    @Override
    public Produto buscarProdutoPorId(int id) throws RemoteException {
        return produtoDAO.buscarPorId(id);
    }

    @Override
    public void inserirProduto(Produto p) throws RemoteException {
        produtoDAO.inserir(p);
    }

    @Override
    public void atualizarProduto(Produto p) throws RemoteException {
        produtoDAO.atualizar(p);
    }

    @Override
    public boolean excluirProduto(int id) throws RemoteException {
        return produtoDAO.deletar(id);
    }

    /* ========================= CATEGORIAS ========================= */

    @Override
    public List<Categoria> listarCategorias() throws RemoteException {
        return categoriaDAO.listar();
    }

    @Override
    public Categoria buscarCategoriaPorId(int id) throws RemoteException {
        return categoriaDAO.buscarPorId(id);
    }

    @Override
    public void inserirCategoria(Categoria c) throws RemoteException {
        categoriaDAO.inserir(c);
    }

    @Override
    public boolean atualizarCategoria(Categoria c) throws RemoteException {
        return categoriaDAO.atualizar(c);
    }

    @Override
    public boolean excluirCategoria(int id) throws RemoteException {
        return categoriaDAO.deletar(id);
    }

    /* ========================= MOVIMENTAÇÕES ========================= */

    @Override
    public boolean registrarMovimentacao(Movimentacao m) throws RemoteException {
        // Insere a movimentação no banco
        boolean sucessoMov = movimentacaoDAO.inserir(m);

        // Atualiza o estoque conforme o tipo (entrada ou saída)
        if (sucessoMov) {
            return movimentacaoDAO.atualizarEstoque(
                    m.getProdutoId(),
                    m.getQuantidade(),
                    m.getTipo()
            );
        }

        return false;
    }
}
