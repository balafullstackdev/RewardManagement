/**
 * 
 */
package com.rewards.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rewards.entity.Transaction;

/**
 * @author bappana
 *
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	/**
	 * 
	 * @param customerId
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	List<Transaction> findAllByCustomerIdAndUpdatedDtBetween(Long customerId, Date fromDate, Date toDate);

}