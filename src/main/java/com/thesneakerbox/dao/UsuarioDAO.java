package com.thesneakerbox.dao;

import com.thesneakerbox.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    Usuario login(String email, String password);

    List<Usuario> findAll();

    Usuario findById(int id);

    List<Usuario> buscar(String nombre, String email);

    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(int id);
}