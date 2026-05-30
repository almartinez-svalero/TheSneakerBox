package com.thesneakerbox.dao;

import com.thesneakerbox.model.Usuario;

public interface UsuarioDAO {

    Usuario login(String email, String password);

}