package com.sdm.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.sdm.dao.ProdutoDAO;
import com.sdm.dao.CategoriaDAO;
import com.sdm.dao.MovimentacaoDAO;
import com.sdm.model.Produto;
import com.sdm.model.Categoria;
import com.sdm.model.Movimentacao;
import java.util.List;

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

    // Produtos
    @Override
    public ArrayList<Produto> listarProdutos() throws RemoteException {
        return produtoDAO.listar();
    }

    @Override
    public Produto buscarProdutoPorId(int id) throws RemoteException {
        return produtoDAO.buscarPorId(id);
    }

    @Override
    public boolean inserirProduto(Produto p) throws RemoteException {
        return produtoDAO.inserir(p);
    }

    @Override
    public boolean atualizarProduto(Produto p) throws RemoteException {
        return produtoDAO.atualizar(p);
    }

    @Override
    public boolean excluirProduto(int id) throws RemoteException {
        return produtoDAO.deletar(id);
    }

    // Categorias
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
        return categoriaDAO.inserir(c);
    }

    @Override
    public boolean atualizarCategoria(Categoria c) throws RemoteException {
        return categoriaDAO.atualizar(c);
    }

    @Override
    public boolean excluirCategoria(int id) throws RemoteException {
        return categoriaDAO.deletar(id);
    }

    // Movimentacoes
    @Override
    public ArrayList<Movimentacao> listarMovimentacoes() throws RemoteException {
        // se seu MovimentacaoDAO tem nome diferente para listar adapte aqui
        return movimentacaoDAO.listar();
    }

    @Override
    public boolean registrarMovimentacao(Movimentacao m) throws RemoteException {
        // Algumas implementações fazem inserir + atualizar estoque separadamente.
        // Aqui assumimos que MovimentacaoDAO.insere e MovimentacaoDAO.atualizarEstoque existem.
        boolean okInsert = movimentacaoDAO.inserir(m);
        if (!okInsert) return false;
        // se existir método atualizarEstoque(int produtoId, int novaQuantidade) ou similar:
        try {
            movimentacaoDAO.atualizarEstoque(m.getProduto().getId(), m.getQuantidade());
        } catch (Exception ex) {
            // se der erro ao atualizar estoque, você pode optar por logar ou reverter
            System.err.println("Aviso: não foi possível atualizar estoque automaticamente: " + ex.getMessage());
        }
        return true;
    }
}