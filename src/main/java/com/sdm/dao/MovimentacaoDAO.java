package com.sdm.dao;

import com.sdm.model.Movimentacao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAO {

    // Método para atualizar a quantidade de estoque
    public boolean atualizarEstoque(int produtoId, int quantidade, String tipo) {
        String sql = "";

        if ("ENTRADA".equalsIgnoreCase(tipo)) {
            sql = "UPDATE produto SET quantidade_estoque = quantidade_estoque + ? WHERE id = ?";
        } else if ("SAIDA".equalsIgnoreCase(tipo)) {
            sql = "UPDATE produto SET quantidade_estoque = quantidade_estoque - ? WHERE id = ?";
        } else {
            System.out.println("Tipo inválido: " + tipo);
            return false;
        }

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantidade);
            stmt.setInt(2, produtoId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para inserir a movimentação no banco de dados
    public boolean inserir(Movimentacao movimentacao) {
        String sql = "INSERT INTO movimentacao (produto_id, quantidade, tipo, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movimentacao.getProdutoId());
            stmt.setInt(2, movimentacao.getQuantidade());
            stmt.setString(3, movimentacao.getTipo());
            stmt.setTimestamp(4, Timestamp.valueOf(movimentacao.getData()));

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 📌 READ - Listar todas as movimentações
    // ================================
    public List<Movimentacao> listar() {
        List<Movimentacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao ORDER BY data DESC";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movimentacao m = new Movimentacao(
                        rs.getInt("id_movimentacao"),
                        rs.getString("nome"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade"),
                        rs.getString("tipo"),
                        rs.getTimestamp("data").toLocalDateTime()
                );

                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ================================
    // 📌 READ - Buscar movimentação por ID
    // ================================
    public Movimentacao buscarPorId(int id) {
        String sql = "SELECT * FROM movimentacao WHERE id_movimentacao = ?";
        Movimentacao mov = null;

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mov = new Movimentacao(
                        rs.getInt("id_movimentacao"),
                        rs.getString("nome"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade"),
                        rs.getString("tipo"),
                        rs.getTimestamp("data").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mov;
    }
}