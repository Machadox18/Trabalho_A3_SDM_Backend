package com.sdm.dao;

import com.sdm.model.Categoria;
import com.sdm.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    // CREATE
    public void inserir(Produto p) {
        String sql = "INSERT INTO produto (nome, preco_unitario, unidade, qtd_estoque, qtd_minima, qtd_maxima, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Preenche os parâmetros
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPrecoUnitario());
            stmt.setString(3, p.getUnidade());
            stmt.setInt(4, p.getQuantidadeEstoque());
            stmt.setInt(5, p.getQuantidadeMinima());
            stmt.setInt(6, p.getQuantidadeMaxima());
            stmt.setInt(7, p.getCategoria().getId());

            // Executa o INSERT
            stmt.executeUpdate();

            // Recupera o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                p.setId(idGerado); // atualiza o objeto com o ID do banco
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    // READ
    public ArrayList<Produto> listar() {
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.nome AS nome_categoria FROM produto p JOIN categoria c ON p.id_categoria = c.id_categoria";

        try (Connection conn = ConexaoDAO.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Categoria cat = new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nome_categoria")
                );

                Produto p = new Produto(
                        rs.getInt("id_produto"),
                        rs.getString("nome"),
                        rs.getDouble("preco_unitario"),
                        rs.getString("unidade"),
                        rs.getInt("qtd_estoque"),
                        rs.getInt("qtd_minima"),
                        rs.getInt("qtd_maxima"),
                        cat
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }


    public boolean reajustarPrecos(double percentual) {
        String sql = "UPDATE produto SET preco_unitario = preco_unitario + (preco_unitario * ? / 100)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, percentual);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao reajustar preços: " + e.getMessage());
            return false;
        }
    }

    public List<Produto> listarPrecos() {
        List<Produto> listaPrecos = new ArrayList<>();
        String sql = "SELECT p.nome, p.preco_unitario, p.unidade, c.nome AS nome_categoria " +
                     "FROM produto p " +
                     "JOIN categoria c ON p.id_categoria = c.id_categoria " +
                     "ORDER BY p.nome ASC";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getString("nome"),
                        rs.getDouble("preco_unitario"),
                        rs.getString("unidade"));

                Categoria c = new Categoria(
                        rs.getString("nome_categoria"));
                        p.setCategoria(c);

                listaPrecos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar preços: " + e.getMessage());
        }

        return listaPrecos;
    }

    // UPDATE
    public void atualizar(Produto p) {
        String sql = "UPDATE produto SET nome=?, preco_unitario=?, unidade=?, qtd_estoque=?, qtd_minima=?, qtd_maxima=?, id_categoria=? WHERE id_produto=?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPrecoUnitario());
            stmt.setString(3, p.getUnidade());
            stmt.setInt(4, p.getQuantidadeEstoque());
            stmt.setInt(5, p.getQuantidadeMinima());
            stmt.setInt(6, p.getQuantidadeMaxima());
            stmt.setInt(7, p.getCategoria().getId());
            stmt.setInt(8, p.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }
    /*
    Método para buscar o produto pelo Id
     */
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id_produto = ?";
        Produto produto = null;

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setPrecoUnitario(rs.getDouble("preco_unitario"));
                produto.setUnidade(rs.getString("unidade"));
                produto.setQuantidadeEstoque(rs.getInt("qtd_estoque"));
                produto.setQuantidadeMinima(rs.getInt("qtd_minima"));
                produto.setQuantidadeMaxima(rs.getInt("qtd_maxima"));
            }

        } catch  (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }

    // DELETE
    public boolean deletar(int id) {
        String sql = "DELETE FROM produto WHERE id_produto=?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
        return false;
    }

}