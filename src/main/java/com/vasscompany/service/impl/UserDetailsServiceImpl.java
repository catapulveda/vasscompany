package com.vasscompany.service.impl;

import com.vasscompany.entities.UsuarioEntity;
import com.vasscompany.repo.UserDetailsRepo;
import com.vasscompany.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "userDetailsServiceImpl")
public class UserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioEntity = userDetailsRepo.findByUsername(s);

        if (!usuarioEntity.isPresent())
            throw new BadCredentialsException("Bad credentials");

        new AccountStatusUserDetailsChecker().check(usuarioEntity.get());

        return usuarioEntity.get();
    }

    @Override
    public UsuarioEntity insert(UsuarioEntity entity) {
        return userDetailsRepo.save(entity);
    }
}
