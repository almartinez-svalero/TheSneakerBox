package com.thesneakerbox.dao;

import com.thesneakerbox.model.Marca;

import java.util.List;

public interface MarcaDAO {

    List<Marca> findAll();

    void save(Marca marca);

    void delete(int id);

}