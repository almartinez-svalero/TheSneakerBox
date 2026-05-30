package com.thesneakerbox.dao;

import com.thesneakerbox.model.Marca;

import java.util.List;

public interface MarcaDAO {

    List<Marca> findAll();

    List<Marca> buscar(String nombre, String pais);

    Marca findById(int id);

    void save(Marca marca);

    void update(Marca marca);

    void delete(int id);

}