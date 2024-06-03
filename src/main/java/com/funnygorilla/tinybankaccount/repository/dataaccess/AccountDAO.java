package com.funnygorilla.tinybankaccount.repository.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author FunnyGorilla
 *
 */
public interface AccountDAO extends JpaRepository<AccountDB, Long> {
	
}
