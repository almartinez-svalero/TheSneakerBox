package com.thesneakerbox.dao;

import com.thesneakerbox.model.Zapatilla;

import java.util.List;

public interface ZapatillaDAO {

    List<Zapatilla> findAll();

    Zapatilla findById(int id);

    void save(Zapatilla zapatilla);

    void update(Zapatilla zapatilla);

    void delete(int id);

}