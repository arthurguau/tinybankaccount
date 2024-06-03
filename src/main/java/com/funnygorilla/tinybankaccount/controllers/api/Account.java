package com.funnygorilla.tinybankaccount.controllers.api;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	private Long accountNo;
	private Long customerId;
	
	@NotNull(message = "Course can not be null.")
	private String alias;
	private BigDecimal balance;
	private String active;	
}
