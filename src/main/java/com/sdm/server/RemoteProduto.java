package com.sdm.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.sdm.model.Produto;
import java.util.ArrayList;

public interface RemoteProduto extends Remote {

    ArrayList<Produto> listar() throws RemoteException;

    Produto buscarPorId(int id) throws RemoteException;

    void inserir(Produto p) throws RemoteException;

    void atualizar(Produto p) throws RemoteException;

    boolean deletar(int id) throws RemoteException;
}