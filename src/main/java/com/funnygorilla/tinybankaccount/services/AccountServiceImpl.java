package com.funnygorilla.tinybankaccount.services;  

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funnygorilla.tinybankaccount.dto.AccountDTO;
import com.funnygorilla.tinybankaccount.repository.AccountRepository;
import com.funnygorilla.tinybankaccount.services.domain.entity.AccountBO;

@Service  
public class AccountServiceImpl implements AccountService {  
	
	@Autowired  
	private AccountRepository accountRepository;  
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<AccountDTO> getAllAccounts(){  
		
		List <AccountDTO> all = new ArrayList<AccountDTO>();  
		List <AccountBO> bos = this.accountRepository.getAllAccounts();
		
		AccountDTO dto = null;
		
		Iterator<AccountBO> r = bos.iterator();
		while (r.hasNext()) {
			dto = new AccountDTO();
			BeanUtils.copyProperties(r.next(), dto);
			all.add(dto);
		}
			
		return all;  
	}  
	
	@Override
	public AccountDTO getAccountByNumber(Long id){  
		logger.debug(" ---------------> getStudentByID() called in Service. ");
		AccountBO studentBO = this.accountRepository.getAccountByNumber(id);
		
		AccountDTO dto = new AccountDTO();
		BeanUtils.copyProperties(studentBO, dto);
		
		return dto;
	}  
	
	@Override
	public Long createAccount(AccountDTO student){  
		logger.debug(" ---------------> [Student Service] createStudent() before save(). ");
		
		//simple data transformation logic
		AccountBO sbo = new AccountBO();
		BeanUtils.copyProperties(student, sbo);
		
		Long id = this.accountRepository.createAccount(sbo);
		logger.debug(" ---------------> [Student Service] createStudent() after save(). ");
		return id;
	}  
	
	@Override
	public AccountDTO updateAccount (AccountDTO student ) {
		AccountBO sbo = new AccountBO();
		BeanUtils.copyProperties(student, sbo);
		
		sbo = this.accountRepository.updateAccount(sbo);
		
		AccountDTO sto = new AccountDTO();
		BeanUtils.copyProperties(sbo, sto);
		
		return  sto;
	}
	
	@Override
	public boolean deleteAccount (Long studentId) {
		return this.accountRepository.deleteAccount(studentId);
	}
}  