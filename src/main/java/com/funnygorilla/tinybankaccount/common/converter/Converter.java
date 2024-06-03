package com.funnygorilla.tinybankaccount.common.converter;

import org.springframework.beans.BeanUtils;

import com.funnygorilla.tinybankaccount.controllers.api.Account;
import com.funnygorilla.tinybankaccount.controllers.api.AccountNumber;
import com.funnygorilla.tinybankaccount.dto.AccountDTO;

public class Converter<S, V>  {

	public static Account fromStudnetDTOToStudent (AccountDTO studentDTO) {
		Account student = new Account();
		// simple converter logic.
	    BeanUtils.copyProperties(studentDTO, student);
	    
	    return student;
    }

    public static AccountDTO fromStudentToStudentDTO (Account student) {
		AccountDTO studentDTO = new AccountDTO();
		// simple converter logic.
	    BeanUtils.copyProperties(student, studentDTO);
	    return studentDTO;
    }
 
	public static AccountNumber fromStudentDTOToStudentNumber (AccountDTO s) {
		if (null == s) return null;
		
		AccountNumber number = new AccountNumber();
		number.setId(s.getAccountNo());
		return number;
	}
	
	public static AccountDTO fromStudentNumberToStudentDTO (AccountNumber sn) {
		 throw new AssertionError("does not support this method!"); 
	}

    
    
}