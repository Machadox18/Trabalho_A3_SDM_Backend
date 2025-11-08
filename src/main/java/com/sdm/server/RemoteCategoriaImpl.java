package com.sdm.server;

import com.sdm.dao.CategoriaDAO;
import com.sdm.model.Categoria;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Implementação remota do serviço de categorias.
 */
public class RemoteCategoriaImpl extends UnicastRemoteObject implements RemoteCategoria {

    private final CategoriaDAO categoriaDAO;

    public RemoteCategoriaImpl() throws RemoteException {
        super();
        this.categoriaDAO = new CategoriaDAO();
    }

    @Override
    public List<Categoria> listar() throws RemoteException {
        return categoriaDAO.listar();
    }

    @Override
    public Categoria buscarPorId(int id) throws RemoteException {
        return categoriaDAO.buscarPorId(id);
    }

    @Override
    public void inserir(Categoria c) throws RemoteException {
        categoriaDAO.inserir(c);
    }

    @Override
    public boolean atualizar(Categoria c) throws RemoteException {
        return categoriaDAO.atualizar(c);
    }

    @Override
    public boolean deletar(int id) throws RemoteException {
        return categoriaDAO.deletar(id);
    }
}