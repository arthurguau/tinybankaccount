package com.funnygorilla.tinybankaccount.controllers.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.funnygorilla.tinybankaccount.dto.AccountDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Account-Domain", description = "provider account management service")
public interface AccountAPI {
	
	@RequestMapping(method = RequestMethod.GET, value="/")
	public ResponseEntity<List<AccountNumber>> getAccounts();
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<AccountDTO> getAccountByNumber(@PathVariable(required=true) long id);
	
	@RequestMapping(method = RequestMethod.POST, value="/")
	public ResponseEntity<Long> createAccount(@RequestBody @Valid AccountDTO student);
	
    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public ResponseEntity<AccountDTO> updateAccount(@PathVariable("id") Long id, 
		@Valid @RequestBody Account student) ;	
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") Long id) ;	

}
