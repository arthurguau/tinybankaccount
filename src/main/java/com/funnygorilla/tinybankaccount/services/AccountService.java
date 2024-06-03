package com.funnygorilla.tinybankaccount.services;  

import java.util.List;

import com.funnygorilla.tinybankaccount.dto.AccountDTO;


public interface AccountService {  
	
	/**
	 * 
	 * @return
	 */
	public List<AccountDTO> getAllAccounts();
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AccountDTO getAccountByNumber(Long id);	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public Long createAccount(AccountDTO accountDTO);
	/**
	 * 
	 * @param student
	 * @return
	 */
	public AccountDTO updateAccount (AccountDTO accountDTO );
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public boolean deleteAccount (Long accountId) ;
}  