package com.sdm.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.sdm.model.Movimentacao;

public interface RemoteMovimentacao extends Remote {

    boolean inserir(Movimentacao m) throws RemoteException;

    boolean atualizarEstoque(int produtoId, int quantidade, String tipo) throws RemoteException;
}