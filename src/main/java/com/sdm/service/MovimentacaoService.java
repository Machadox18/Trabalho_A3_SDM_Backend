package com.sdm.service;

import com.sdm.dao.MovimentacaoDAO;
import com.sdm.model.Movimentacao;
import com.sdm.dao.ProdutoDAO;

import java.time.LocalDateTime;

public class MovimentacaoService {
    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    /*
    Método para registrar a movimentação
     */
    public boolean registrarEstoque(int produtoId, int quantidade, String tipo, String dataStr) {
        /*
        1. Cria objeto movimentação
         */
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setProdutoId(produtoId);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setTipo(tipo);

        /*
        Definir data
         */
        LocalDateTime data;
        if (dataStr != null && !dataStr.isEmpty()) {
            data = LocalDateTime.parse(dataStr);
        } else {
            data = LocalDateTime.now();
        }
        movimentacao.setData(data);

        /*
        2. Envia solicitação de atualização de estoque ao DAO
         */
        boolean estoqueAtualizado = movimentacaoDAO.atualizarEstoque(produtoId, quantidade, tipo);

        if (!estoqueAtualizado) { //Se não atualizou, retorna falso
            return false;
        }

        return movimentacaoDAO.inserir(movimentacao); /*Avisa para o DAO inserir as movimentações
                                                        no banco de dados
                                                      */
    }

}





