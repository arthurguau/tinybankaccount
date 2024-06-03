package com.funnygorilla.tinybankaccount.services.domain.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountBO {
	private Long accountNo;
	private Long customerId;
	private String alias;
	private BigDecimal balance;
	private String active;	
}
