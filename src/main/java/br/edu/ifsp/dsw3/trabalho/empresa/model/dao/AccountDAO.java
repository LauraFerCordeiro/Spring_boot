package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;

public interface AccountDAO extends JpaRepository<Account, Long>{
    @Query("select a from Account a where a.email = ?1")
    public Account findByEmail (String email);
}

