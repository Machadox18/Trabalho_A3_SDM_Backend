package com.sdm.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Inicializa o servidor RMI e registra cada serviço remotamente e de forma independente.
 */
public class RMIServer {

    public static void main(String[] args) {
        try {
            int port = 1099;
            Registry registry = LocateRegistry.createRegistry(port);

            // Cria e registra cada serviço separadamente
            RemoteProduto produtoService = new RemoteProdutoImpl();
            RemoteCategoria categoriaService = new RemoteCategoriaImpl();
            RemoteMovimentacao movimentacaoService = new RemoteMovimentacaoImpl();
            RemoteRelatorio relatorioService = new RemoteRelatorioImpl();

            registry.rebind("ProdutoService", produtoService);
            registry.rebind("CategoriaService", categoriaService);
            registry.rebind("MovimentacaoService", movimentacaoService);
            registry.rebind("RelatorioService", new RemoteRelatorioImpl());

            System.out.println("Servidor RMI iniciado na porta " + port);
            System.out.println("Serviços registrados:");
            System.out.println("   ProdutoService");
            System.out.println("   CategoriaService");
            System.out.println("   MovimentacaoService");
            System.out.println("   RelatorioService");

        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor RMI:");
            e.printStackTrace();
        }
    }
}