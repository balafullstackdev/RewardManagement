/**
 * 
 */
package com.rewards.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bappana
 *
 */
@Data
@AllArgsConstructor
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID")
	private Long transactionId;
	
	@Column(name="CUSTOMER_ID")
	private Long customerId;

	@Column(name = "UPDATED_DT")
	private Date updatedDt;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	public Transaction() {
	}

}
