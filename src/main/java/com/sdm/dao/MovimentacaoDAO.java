package com.sdm.dao;

import com.sdm.model.Movimentacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
}
