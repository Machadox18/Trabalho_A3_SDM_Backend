package com.sdm.server;

import com.sdm.model.Categoria;
import com.sdm.model.Produto;
import com.sdm.service.RelatorioService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RemoteRelatorioImpl extends UnicastRemoteObject implements RemoteRelatorio{
    private final RelatorioService relatorioService;

    public RemoteRelatorioImpl() throws RemoteException {
        super();
        this.relatorioService = new RelatorioService();
    }

    @Override
    public List<Produto> produtosAbaixoMinimo() throws RemoteException {
        return relatorioService.listarAbaixoMinimo();
    }

    @Override
    public List<Produto> produtosAcimaMaximo() throws RemoteException {
        return relatorioService.produtosAcimaMaximo();
    }

    @Override
    public List<Produto> relatorioMovimentacoes() throws RemoteException {
        return relatorioService.listarMovimentacaoProduto();
    }

    @Override
    public List<Produto> relatorioBalanco() throws RemoteException {
        return relatorioService.listarBalancoEstoque();
    }


    public List<Produto> listarPrecos() throws RemoteException {
        return relatorioService.listarPrecos();
    }


    public List<Categoria> produtosPorCategoria() throws RemoteException {
        return relatorioService.produtoPorCategoria();
    }
}
