package com.thesneakerbox.dao;

import com.thesneakerbox.model.Zapatilla;

import java.util.List;

public interface ZapatillaDAO {

    List<Zapatilla> findAll();

    Zapatilla findById(int id);

    List<Zapatilla> buscar(String nombre, String color);

    void save(Zapatilla zapatilla);

    void update(Zapatilla zapatilla);

    void delete(int id);

}