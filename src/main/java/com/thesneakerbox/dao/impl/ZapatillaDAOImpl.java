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
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Zapatilla zapatilla = new Zapatilla();

                zapatilla.setId(resultSet.getInt("id"));
                zapatilla.setNombre(resultSet.getString("nombre"));
                zapatilla.setPrecio(resultSet.getDouble("precio"));
                zapatilla.setStock(resultSet.getInt("stock"));
                zapatilla.setColor(resultSet.getString("color"));
                zapatilla.setImagen(resultSet.getString("imagen"));
                zapatilla.setMarcaId(resultSet.getInt("marca_id"));
                zapatilla.setNombreMarca(resultSet.getString("nombre_marca"));

                zapatillas.add(zapatilla);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return zapatillas;
    }

    @Override
    public Zapatilla findById(int id) {

        String sql = "SELECT z.*, m.nombre AS nombre_marca\n" +
                "FROM zapatillas z\n" +
                "JOIN marcas m ON z.marca_id = m.id\n" +
                "WHERE z.id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Zapatilla zapatilla = new Zapatilla();

                zapatilla.setId(resultSet.getInt("id"));
                zapatilla.setNombre(resultSet.getString("nombre"));
                zapatilla.setNombreMarca(resultSet.getString("nombre_marca"));
                zapatilla.setPrecio(resultSet.getDouble("precio"));
                zapatilla.setStock(resultSet.getInt("stock"));
                zapatilla.setColor(resultSet.getString("color"));
                zapatilla.setImagen(resultSet.getString("imagen"));
                zapatilla.setMarcaId(resultSet.getInt("marca_id"));

                return zapatilla;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void save(Zapatilla zapatilla) {

        String sql = """
                INSERT INTO zapatillas
                (nombre, precio, stock, color, imagen, marca_id)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, zapatilla.getNombre());
            statement.setDouble(2, zapatilla.getPrecio());
            statement.setInt(3, zapatilla.getStock());
            statement.setString(4, zapatilla.getColor());
            statement.setString(5, zapatilla.getImagen());
            statement.setInt(6, zapatilla.getMarcaId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Zapatilla zapatilla) {

        String sql = """
                UPDATE zapatillas
                SET nombre = ?,
                    precio = ?,
                    stock = ?,
                    color = ?,
                    imagen = ?,
                    marca_id = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, zapatilla.getNombre());
            statement.setDouble(2, zapatilla.getPrecio());
            statement.setInt(3, zapatilla.getStock());
            statement.setString(4, zapatilla.getColor());
            statement.setString(5, zapatilla.getImagen());
            statement.setInt(6, zapatilla.getMarcaId());
            statement.setInt(7, zapatilla.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql =
                "DELETE FROM zapatillas WHERE id = ?";

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

    @Override
    public List<Zapatilla> buscar(String nombre, String color) {

        List<Zapatilla> zapatillas =
                new ArrayList<>();

        String sql = """
                SELECT z.*,
                       m.nombre AS nombre_marca
                FROM zapatillas z
                INNER JOIN marcas m
                    ON z.marca_id = m.id
                WHERE z.nombre LIKE ?
                AND z.color LIKE ?
                """;

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, "%" + nombre + "%");
            statement.setString(2, "%" + color + "%");

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Zapatilla zapatilla =
                        new Zapatilla();

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