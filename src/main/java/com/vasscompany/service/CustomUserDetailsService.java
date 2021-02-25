package com.vasscompany.service;

import com.vasscompany.entities.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {

    UsuarioEntity insert(UsuarioEntity entity);
}
