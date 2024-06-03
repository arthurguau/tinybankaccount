package com.funnygorilla.tinybankaccount.repository.dataaccess;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity  
@Table(name = "Account")
public class AccountDB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="account_no", updatable=false, nullable = false)
	private Long accountNo;
	
	@Column(name="customer_id", updatable=false, nullable = false)
	private Long customerId;
	
	@Column(name = "alias")
	private String alias;
	
	@Column(name = "balance", nullable = false, columnDefinition = "Decimal(20,2) default '0.00'")
	private BigDecimal balance = BigDecimal.ZERO;
	
	@Column(name = "active", nullable = false, columnDefinition = "String default true")
	private String active;	
}
