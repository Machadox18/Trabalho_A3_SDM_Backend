package com.sdm.service;

import com.sdm.dao.MovimentacaoDAO;
import com.sdm.model.Movimentacao;
import com.sdm.dao.ProdutoDAO;
import com.sdm.model.Produto;
import java.time.LocalDateTime;

public class MovimentacaoService {
    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    /*
    Método para registrar a movimentação e mostrar se o produto está acima do máximo
    ou abaixo do mínimo
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
        /*
        Mostra aviso se a quantidade em estoque de determinado produto está acima da quantidade máxima
        ou abaixo da quantidade mínima
         */
        Produto produto = produtoDAO.buscarPorId(movimentacao.getProdutoId());
        String statusProduto = "Status: ";
        if (produto.getQuantidadeEstoque() > produto.getQuantidadeMaxima()) {
            statusProduto += String.format("A quantidade do produto %s está acima da quantidade máxima", produto.getNome());
        } else if (produto.getQuantidadeEstoque() < produto.getQuantidadeMinima()) {
            statusProduto += String.format("A quantidade do produto %s está abaixo da quantidade mínima", produto.getNome());
        }

        return movimentacaoDAO.inserir(movimentacao); /*Avisa para o DAO inserir as movimentações
                                                        no banco de dados*/
    }
}





