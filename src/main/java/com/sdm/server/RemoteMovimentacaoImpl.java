package com.sdm.server;

import com.sdm.dao.MovimentacaoDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementação remota do serviço de movimentações.
 */
public class RemoteMovimentacaoImpl extends UnicastRemoteObject implements RemoteMovimentacao {

    private final MovimentacaoDAO movimentacaoDAO;

    public RemoteMovimentacaoImpl() throws RemoteException {
        super();
        this.movimentacaoDAO = new MovimentacaoDAO();
    }

    @Override
    public boolean registrarMovimentacao(int produtoId, int quantidade, String tipo, String dataStr) throws RemoteException {
        return movimentacaoDAO.atualizarEstoque(produtoId, quantidade, tipo);
    }
}