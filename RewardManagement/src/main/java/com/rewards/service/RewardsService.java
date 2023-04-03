/**
 * 
 */
package com.rewards.service;

import java.util.List;

import com.rewards.model.CustomerRewards;

/**
 * @author bappana
 *
 */
public interface RewardsService {
	/**
	 * 
	 * @param customerId
	 * @return
	 */
	CustomerRewards getCustomerRewards(Long customerId);

	/**
	 * 
	 * @return
	 */
	List<CustomerRewards> getAllCustomerRewards();

}
