package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Account;

public interface AccountDAO extends JpaRepository<Account, Long>{

    
}
