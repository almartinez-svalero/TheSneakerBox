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

            System.out.println("ANTES DE CONEXION");

            Connection connection = DBConnection.getConnection();

            System.out.println("CONEXION OK");

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            System.out.println("STATEMENT OK");

            ResultSet resultSet = statement.executeQuery();

            System.out.println("CONSULTA EJECUTADA");

            while (resultSet.next()) {

                System.out.println("FILA ENCONTRADA");

                Marca marca = new Marca();

                marca.setId(resultSet.getInt("id"));
                marca.setNombre(resultSet.getString("nombre"));
                marca.setPais(resultSet.getString("pais"));
                marca.setPremium(resultSet.getBoolean("premium"));
                marca.setLogo(resultSet.getString("logo"));

                marcas.add(marca);
            }

            System.out.println("TOTAL EN DAO: " + marcas.size());

        } catch (Exception e) {

            System.out.println("ERROR EN DAO");

            e.printStackTrace();
        }

        return marcas;
    }
}