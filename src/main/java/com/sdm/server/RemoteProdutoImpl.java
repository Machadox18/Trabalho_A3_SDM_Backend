package com.sdm.server;

import com.sdm.dao.ProdutoDAO;
import com.sdm.model.Produto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RemoteProdutoImpl extends UnicastRemoteObject implements RemoteProduto {

    private final ProdutoDAO produtoDAO;

    public RemoteProdutoImpl() throws RemoteException {
        super();
        this.produtoDAO = new ProdutoDAO();
    }

    @Override
    public ArrayList<Produto> listar() throws RemoteException {
        return produtoDAO.listar();
    }

    @Override
    public Produto buscarPorId(int id) throws RemoteException {
        return produtoDAO.buscarPorId(id);
    }

    @Override
    public void inserir(Produto p) throws RemoteException {
        produtoDAO.inserir(p);
    }

    @Override
    public void atualizar(Produto p) throws RemoteException {
        produtoDAO.atualizar(p);
    }

    @Override
    public boolean deletar(int id) throws RemoteException {
        return produtoDAO.deletar(id);
    }
}