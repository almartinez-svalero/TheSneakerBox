package com.thesneakerbox.dao.impl;

import com.thesneakerbox.dao.ZapatillaDAO;
import com.thesneakerbox.model.Zapatilla;
import com.thesneakerbox.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ZapatillaDAOImpl implements ZapatillaDAO {

    @Override
    public List<Zapatilla> findAll() {

        List<Zapatilla> zapatillas = new ArrayList<>();

        String sql = """
                SELECT z.*,
                       m.nombre AS nombre_marca
                FROM zapatillas z
                INNER JOIN marcas m
                    ON z.marca_id = m.id
                """;

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Zapatilla zapatilla = new Zapatilla();

                zapatilla.setId(
                        resultSet.getInt("id")
                );

                zapatilla.setNombre(
                        resultSet.getString("nombre")
                );

                zapatilla.setPrecio(
                        resultSet.getDouble("precio")
                );

                zapatilla.setStock(
                        resultSet.getInt("stock")
                );

                zapatilla.setColor(
                        resultSet.getString("color")
                );

                if (resultSet.getDate("fecha_lanzamiento") != null) {

                    zapatilla.setFechaLanzamiento(
                            resultSet.getDate("fecha_lanzamiento")
                                    .toLocalDate()
                    );
                }

                zapatilla.setEdicionLimitada(
                        resultSet.getBoolean("edicion_limitada")
                );

                zapatilla.setImagen(
                        resultSet.getString("imagen")
                );

                zapatilla.setMarcaId(
                        resultSet.getInt("marca_id")
                );

                zapatilla.setNombreMarca(
                        resultSet.getString("nombre_marca")
                );

                zapatillas.add(zapatilla);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return zapatillas;
    }
}