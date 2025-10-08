package com.sdm.service;

import com.sdm.dao.ProdutoDAO;
import com.sdm.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    //Cadastrar ou atualizar produto
    public void salvar(Produto produto) {
        if (produto.getId() == 0) {
            produtoDAO.inserir(produto);
        } else {
            produtoDAO.atualizar(produto);
        }
    }

    //Listar todos os produtos
    public List<Produto> listar() {
        return produtoDAO.listar();
    }

    //Excluir produto
    public boolean excluir(int id) {
        return produtoDAO.deletar(id);
    }

    //Reajustar preços de todos os produtos
    public boolean reajustarPrecos(double percentual) {
        return produtoDAO.reajustarPrecos(percentual);
    }

    //Verificar produtos abaixo do estoque mínimo
    public List<Produto> listarProdutosAbaixoMinimo() {
        List<Produto> produtos = produtoDAO.listar();
        List<Produto> abaixo = new ArrayList<>();

        for (Produto p : produtos) {
            if (p.getQuantidadeEstoque() < p.getQuantidadeMinima()) {
                abaixo.add(p);
            }
        }

        return abaixo;
    }

    //Verificar produtos acima do estoque máximo
    public List<Produto> listarProdutosAcimaMaximo() {
        List<Produto> produtos = produtoDAO.listar();
        List<Produto> acima = new ArrayList<>();

        for (Produto p : produtos) {
            if (p.getQuantidadeEstoque() > p.getQuantidadeMaxima()) {
                acima.add(p);
            }
        }

        return acima;
    }

    //Verificar situação individual de estoque (retorna mensagem)
    public String verificarEstoque(Produto p) {
        if (p.getQuantidadeEstoque() < p.getQuantidadeMinima()) {
            return "⚠️ Produto abaixo do estoque mínimo!";
        } else if (p.getQuantidadeEstoque() > p.getQuantidadeMaxima()) {
            return "⚠️ Produto acima do estoque máximo!";
        } else {
            return "✅ Estoque dentro do limite.";
        }
    }
}
