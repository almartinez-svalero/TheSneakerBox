package com.thesneakerbox.dao.impl;

import com.thesneakerbox.dao.MarcaDAO;
import com.thesneakerbox.model.Marca;
import com.thesneakerbox.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAOImpl implements MarcaDAO {

    @Override
    public List<Marca> findAll() {

        List<Marca> marcas = new ArrayList<>();

        String sql = "SELECT * FROM marcas";

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Marca marca = new Marca();

                marca.setId(resultSet.getInt("id"));
                marca.setNombre(resultSet.getString("nombre"));
                marca.setPais(resultSet.getString("pais"));
                marca.setPremium(resultSet.getBoolean("premium"));

                if (resultSet.getDate("fecha_creacion") != null) {

                    marca.setFechaCreacion(
                            resultSet.getDate("fecha_creacion")
                                    .toLocalDate()
                    );
                }

                marca.setLogo(resultSet.getString("logo"));

                marcas.add(marca);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return marcas;
    }

    @Override
    public Marca findById(int id) {

        String sql = "SELECT * FROM marcas WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Marca marca = new Marca();

                marca.setId(resultSet.getInt("id"));
                marca.setNombre(resultSet.getString("nombre"));
                marca.setPais(resultSet.getString("pais"));
                marca.setPremium(resultSet.getBoolean("premium"));
                marca.setLogo(resultSet.getString("logo"));

                return marca;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void save(Marca marca) {

        String sql =
                "INSERT INTO marcas(nombre, pais, premium, logo) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, marca.getNombre());
            statement.setString(2, marca.getPais());
            statement.setBoolean(3, marca.isPremium());
            statement.setString(4, marca.getLogo());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Marca marca) {

        String sql =
                "UPDATE marcas SET nombre=?, pais=?, premium=?, logo=? WHERE id=?";

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, marca.getNombre());
            statement.setString(2, marca.getPais());
            statement.setBoolean(3, marca.isPremium());
            statement.setString(4, marca.getLogo());
            statement.setInt(5, marca.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM marcas WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();

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