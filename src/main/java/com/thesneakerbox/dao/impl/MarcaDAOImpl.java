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

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

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

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return marcas;
    }

    @Override
    public void save(Marca marca) {

        String sql =
                "INSERT INTO marcas(nombre, pais, premium, logo) VALUES (?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, marca.getNombre());
            statement.setString(2, marca.getPais());
            statement.setBoolean(3, marca.isPremium());
            statement.setString(4, marca.getLogo());

            statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}