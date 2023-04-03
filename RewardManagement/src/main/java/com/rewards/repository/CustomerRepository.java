/**
 * 
 */
package com.rewards.repository;

import org.springframework.data.repository.CrudRepository;

import com.rewards.entity.Customer;

/**
 * @author bappana
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	/**
	 * 
	 * @param customerId
	 * @return
	 */
	Customer findByCustomerId(Long customerId);

}
