package com.sdm.service;

import com.sdm.dao.MovimentacaoDAO;
import com.sdm.dao.ProdutoDAO;

import com.sdm.model.Movimentacao;
import com.sdm.model.Produto;

import java.time.LocalDateTime;

public class MovimentacaoService {
    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean registrarMovimentacao(int produtoId, int quantidade, String tipo, String dataStr) {

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setProdutoId(produtoId);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setTipo(tipo);

        LocalDateTime data;
        if (dataStr != null && !dataStr.isEmpty()) {
            data = LocalDateTime.parse(dataStr);
        } else {
            data = LocalDateTime.now();
        }
        movimentacao.setData(data);

        String statusProduto = "Status: ";
        boolean estoqueAtualizado = movimentacaoDAO.atualizarEstoque(produtoId, quantidade, statusProduto);
        Produto produto = produtoDAO.buscarPorId(produtoId);
        if ("ENTRADA".equalsIgnoreCase(tipo) && produto.getQuantidadeEstoque() > produto.getQuantidadeMaxima()) {
                statusProduto += String.format("O produto %s está acima da quantidade máxima", produto.getNome());
        } else if ("SAIDA".equalsIgnoreCase(tipo) && produto.getQuantidadeEstoque() < produto.getQuantidadeMinima()) {
                statusProduto += String.format("O produto %s está abaixo da quantidade mínima", produto.getNome());
            }

        if (!estoqueAtualizado) {
            return false;
        }

        return movimentacaoDAO.inserir(movimentacao);

    }
}





