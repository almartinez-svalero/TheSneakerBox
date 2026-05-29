package com.thesneakerbox.dao;

import com.thesneakerbox.model.Zapatilla;

import java.util.List;

public interface ZapatillaDAO {

    List<Zapatilla> findAll();

    void save(Zapatilla zapatilla);

    void delete(int id);

}