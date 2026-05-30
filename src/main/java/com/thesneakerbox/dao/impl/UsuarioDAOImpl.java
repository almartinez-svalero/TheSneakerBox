package com.thesneakerbox.dao.impl;

import com.thesneakerbox.dao.UsuarioDAO;
import com.thesneakerbox.model.Usuario;
import com.thesneakerbox.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setRol(resultSet.getString("rol"));
                usuario.setActivo(resultSet.getBoolean("activo"));

                return usuario;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Usuario> findAll() {

        List<Usuario> usuarios =
                new ArrayList<>();

        String sql =
                "SELECT * FROM usuarios";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Usuario usuario =
                        new Usuario();

                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setRol(resultSet.getString("rol"));
                usuario.setActivo(resultSet.getBoolean("activo"));

                usuarios.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public Usuario findById(int id) {

        String sql =
                "SELECT * FROM usuarios WHERE id = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                Usuario usuario =
                        new Usuario();

                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setRol(resultSet.getString("rol"));
                usuario.setActivo(resultSet.getBoolean("activo"));

                return usuario;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Usuario> buscar(String nombre, String email) {

        List<Usuario> usuarios =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM usuarios
                WHERE nombre LIKE ?
                AND email LIKE ?
                """;

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, "%" + nombre + "%");
            statement.setString(2, "%" + email + "%");

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Usuario usuario =
                        new Usuario();

                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setRol(resultSet.getString("rol"));
                usuario.setActivo(resultSet.getBoolean("activo"));

                usuarios.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public void save(Usuario usuario) {

        String sql = """
                INSERT INTO usuarios
                (nombre, email, password, rol, activo, fecha_registro)
                VALUES (?, ?, ?, ?, ?, CURDATE())
                """;

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getRol());
            statement.setBoolean(5, usuario.isActivo());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Usuario usuario) {

        String sql = """
                UPDATE usuarios
                SET nombre = ?,
                    email = ?,
                    password = ?,
                    rol = ?,
                    activo = ?
                WHERE id = ?
                """;

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getRol());
            statement.setBoolean(5, usuario.isActivo());
            statement.setInt(6, usuario.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql =
                "DELETE FROM usuarios WHERE id = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}