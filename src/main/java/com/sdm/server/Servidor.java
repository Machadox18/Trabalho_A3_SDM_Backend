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

        // Define a porta que o servidor vai “ouvir”
        int porta = 12345;

        // Cria uma instância do serviço de produtos (responsável pelo CRUD)
        ProdutoService produtoService = new ProdutoService();

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado na porta " + porta);

            // Mantém o servidor em execução contínua
            while (true) {

                // Aguarda uma conexão do cliente
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                try (
                        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
                ) {
                    String comando = (String) entrada.readObject();

                    switch (comando) {

                        // Caso o cliente queira listar todos os produtos
                        case "listarProdutos":
                            List<Produto> produtos = produtoService.listar();
                            saida.writeObject(produtos);
                            break;

                        // Caso o cliente queira salvar um novo produto
                        case "salvarProduto":
                            Produto p = (Produto) entrada.readObject();
                            produtoService.salvar(p);
                            saida.writeObject("Produto salvo com sucesso!");
                            break;

                        // Caso o comando enviado não seja reconhecido
                        default:
                            saida.writeObject("Comando não reconhecido.");
                            break;
                    }

                } catch (Exception e) {

                    // Exibe erros de comunicação ou processamento
                    e.printStackTrace();
                } finally {

                    // Fecha a conexão com o cliente após atender a requisição
                    socket.close();
                }
            }

        } catch (IOException e) {

            // Caso o comando enviado não seja reconhecido
            e.printStackTrace();
        }
    }
}
