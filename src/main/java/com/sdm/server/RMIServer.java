package com.sdm.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe principal do servidor RMI.
 * Responsável por criar o registro RMI e disponibilizar o serviço EstoqueRemote.
 */
public class RMIServer {

    public static void main(String[] args) {
        try {
            // Porta do serviço (padrão 1099)
            int port = 1099;

            // Cria a instância do serviço
            EstoqueImpl estoqueService = new EstoqueImpl();

            // Cria o registro RMI na porta especificada
            Registry registry = LocateRegistry.createRegistry(port);

            // Registra o serviço com o nome "EstoqueService"
            registry.rebind("EstoqueService", estoqueService);

            System.out.println("Servidor RMI iniciado na porta " + port);
            System.out.println("Serviço registrado como 'EstoqueService'");
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor RMI:");
            e.printStackTrace();
        }
    }
}