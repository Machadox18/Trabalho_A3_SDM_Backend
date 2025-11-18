package com.sdm.server;

import com.sdm.model.Produto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RemoteRelatorio extends Remote {

    List<Produto> produtosAbaixoMinimo() throws RemoteException;

    List<Produto> produtosAcimaMaximo() throws RemoteException;

    /**
     * Retorna lista com estatísticas de movimentação (ex.: nome, totalSaidas, totalEntradas).
     */
    List<Produto> relatorioMovimentacoes() throws RemoteException;

    /**
     * Retorna um mapa com:
     *  - "produtos" -> List<Produto> (cada produto com valores necessários)
     *  - "valor_total_estoque" -> Double
     */
    List<Produto> relatorioBalanco() throws RemoteException;
}
