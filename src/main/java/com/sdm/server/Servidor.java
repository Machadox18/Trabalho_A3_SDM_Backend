package com.sdm.server;

import com.sdm.model.Produto;
import com.sdm.service.ProdutoService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {
    public static void main(String[] args) {
        int porta = 12345;
        ProdutoService produtoService = new ProdutoService();

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado na porta " + porta);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                try (
                        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
                ) {
                    String comando = (String) entrada.readObject();

                    switch (comando) {
                        case "listarProdutos":
                            List<Produto> produtos = produtoService.listar();
                            saida.writeObject(produtos);
                            break;

                        case "salvarProduto":
                            Produto p = (Produto) entrada.readObject();
                            produtoService.salvar(p);
                            saida.writeObject("Produto salvo com sucesso!");
                            break;

                        default:
                            saida.writeObject("Comando não reconhecido.");
                            break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    socket.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
