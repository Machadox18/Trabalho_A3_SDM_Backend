package com.sdm.service;

import com.sdm.dao.ProdutoDAO;
import com.sdm.model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    // --- Relatório 1: Produtos abaixo do estoque mínimo ---
    public List<Produto> produtosAbaixoMinimo() {
        List<Produto> todos = produtoDAO.listar();
        List<Produto> abaixoMinimo = new ArrayList<>();

        for (Produto p : todos) {
            if (p.getQuantidadeEstoque() < p.getQuantidadeMinima()) {
                abaixoMinimo.add(p);
            }
        }

        return abaixoMinimo;
    }

    // --- Relatório 2: Produtos acima do estoque máximo ---
    public List<Produto> produtosAcimaMaximo() {
        List<Produto> todos = produtoDAO.listar();
        List<Produto> acimaMaximo = new ArrayList<>();

        for (Produto p : todos) {
            if (p.getQuantidadeEstoque() > p.getQuantidadeMaxima()) {
                acimaMaximo.add(p);
            }
        }

        return acimaMaximo;
    }

    // --- Relatório 3: Balanço físico/financeiro ---
    public Map<String, Object> gerarBalanco() {
        List<Produto> produtos = produtoDAO.listar();
        Map<String, Object> balanco = new HashMap<>();

        int totalItens = 0;
        double valorTotal = 0.0;

        for (Produto p : produtos) {
            totalItens += p.getQuantidadeEstoque();
            valorTotal += p.getQuantidadeEstoque() * p.getPrecoUnitario();
        }

        balanco.put("total_itens", totalItens);
        balanco.put("valor_total", valorTotal);
        balanco.put("quantidade_produtos", produtos.size());

        return balanco;
    }

    // --- Relatório 4: Lista de todos os produtos com preço e estoque ---
    public List<String> listaDePrecos() {
        List<Produto> produtos = produtoDAO.listar();
        List<String> lista = new ArrayList<>();

        for (Produto p : produtos) {
            lista.add(String.format("Produto: %-20s | Preço: R$ %.2f | Estoque: %d",
                    p.getNome(), p.getPrecoUnitario(), p.getQuantidadeEstoque()));
        }

        return lista;
    }
}
