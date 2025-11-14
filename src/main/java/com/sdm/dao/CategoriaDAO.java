package com.sdm.dao;

import com.sdm.model.Categoria; // Importa a classe Categoria para poder usá-la aqui.
import com.sdm.model.EmbalagemProduto;
import com.sdm.model.TamanhoProduto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public void inserir(Categoria categoria) {
        String sql = "INSERT INTO categoria (nome, tamanho, embalagem) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getTamanho().name());
            stmt.setString(3, categoria.getEmbalagem().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Categoria categoria) {
        if (categoria.getId() == 0) {
            inserir(categoria); // chama o método já existente
        } else {
            atualizar(categoria); // atualiza se já tiver ID
        }
    }

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (Connection conn = ConexaoDAO.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome"));
                categoria.setTamanho(TamanhoProduto.valueOf(rs.getString("tamanho")));
                categoria.setEmbalagem(EmbalagemProduto.valueOf(rs.getString("embalagem")));

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    public List<Categoria> produtoPorCategoria() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT c.nome AS nome_categoria, COUNT(DISTINCT p.id_produto) AS qtd_produtos " +
                     "FROM produto p " +
                     "JOIN categoria c ON p.id_categoria = c.id_categoria " +
                     "GROUP BY c.nome " +
                     "ORDER BY c.nome ASC";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria cat = new Categoria(
                        rs.getString("nome_categoria"),
                        rs.getInt("qtd_produtos")
                );

                lista.add(cat);

            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos por categoria: " + e.getMessage());
        }

        return lista;

    }

    public boolean atualizar(Categoria categoriaAtualizada) {
        String sql = "UPDATE categoria SET nome = ?, tamanho = ?, embalagem = ? WHERE id_categoria = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoriaAtualizada.getNome());
            stmt.setString(2, categoriaAtualizada.getTamanho().name());
            stmt.setString(3, categoriaAtualizada.getEmbalagem().name());
            stmt.setInt(4, categoriaAtualizada.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        Categoria categoria = null;

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome"));
                categoria.setTamanho(TamanhoProduto.valueOf(rs.getString("tamanho")));
                categoria.setEmbalagem(EmbalagemProduto.valueOf(rs.getString("embalagem")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}