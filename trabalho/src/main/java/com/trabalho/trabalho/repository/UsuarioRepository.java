package com.trabalho.trabalho.repository;

import com.trabalho.trabalho.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
   public Usuario findByUsername(String username);

}
