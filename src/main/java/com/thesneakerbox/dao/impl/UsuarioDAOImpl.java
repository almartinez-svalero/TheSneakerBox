package com.thesneakerbox.dao.impl;

import com.thesneakerbox.dao.UsuarioDAO;
import com.thesneakerbox.model.Usuario;
import com.thesneakerbox.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario login(String email, String password) {

        String sql = """
                SELECT *
                FROM usuarios
                WHERE email = ?
                AND password = ?
                AND activo = true
                """;

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(
                        resultSet.getInt("id")
                );

                usuario.setNombre(
                        resultSet.getString("nombre")
                );

                usuario.setEmail(
                        resultSet.getString("email")
                );

                usuario.setRol(
                        resultSet.getString("rol")
                );

                usuario.setActivo(
                        resultSet.getBoolean("activo")
                );

                return usuario;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}