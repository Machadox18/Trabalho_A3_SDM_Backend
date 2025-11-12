package com.sdm.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDAO {
    /**
     * URL de conexão com o banco de dados, lida de {@code config.properties}.
     */
    private static String URL;

    /**
     * Usuário do banco de dados, lido de {@code config.properties}.
     */
    private static String USER;

    /**
     * Senha do banco de dados, lida de {@code config.properties}.
     */
    private static String PASSWORD;

    static {
        try {
            Properties props = new Properties();
            InputStream input = ConexaoDAO.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado.");
            }

            props.load(input);
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar configurações do banco.", e);
        }
    }

    /**
     * Retorna uma nova conexão com o banco de dados utilizando as configurações
     * carregadas de {@code config.properties}.
     *
     * @return instância de {@link Connection} conectada ao banco de dados
     * @throws SQLException se ocorrer erro ao obter a conexão
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}