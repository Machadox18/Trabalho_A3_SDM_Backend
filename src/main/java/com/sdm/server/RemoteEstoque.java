package com.sdm.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface principal do servidor RMI.
 * Fornece acesso remoto aos serviços de Produto, Categoria e Movimentação.
 */
public interface RemoteEstoque extends Remote {

    RemoteProduto getProdutoService() throws RemoteException;

    RemoteCategoria getCategoriaService() throws RemoteException;

    RemoteMovimentacao getMovimentacaoService() throws RemoteException;
}