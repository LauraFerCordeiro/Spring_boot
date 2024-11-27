package br.edu.ifsp.dsw3.trabalho.empresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.AccountDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;

@Service
public class AccountDetailService implements UserDetailsService{
    @Autowired
    private AccountDAO adao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account a = adao.findByEmail(username);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername(username)
            .password(encoder.encode(a.getPassword()))
            .roles(a.getRole().getDescricao())
            .build();
        return user;
    }
    
}
