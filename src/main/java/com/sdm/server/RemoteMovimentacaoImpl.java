package com.sdm.server;

import com.sdm.dao.MovimentacaoDAO;
import com.sdm.model.Movimentacao;
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
    public boolean inserir(Movimentacao m) throws RemoteException {
        boolean sucesso = movimentacaoDAO.inserir(m);
        if (sucesso) {
            return movimentacaoDAO.atualizarEstoque(
                m.getProdutoId(),
                m.getQuantidade(),
                m.getTipo()
            );
        }
        return false;
    }

    @Override
    public boolean atualizarEstoque(int produtoId, int quantidade, String tipo) throws RemoteException {
        return movimentacaoDAO.atualizarEstoque(produtoId, quantidade, tipo);
    }
}