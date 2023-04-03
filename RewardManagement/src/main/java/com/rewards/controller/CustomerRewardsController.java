package com.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.controller.exception.CustomerNotFoundException;
import com.rewards.model.CustomerRewards;
import com.rewards.service.RewardsService;

/**
 * This controller provides rewards details for customer
 * 
 * @author bappana
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomerRewardsController {

	@Autowired
	private RewardsService rewardsService;

	@GetMapping("/{customerId}/rewards")
	public ResponseEntity<CustomerRewards> getCustomerRewards(@PathVariable Long customerId)
			throws CustomerNotFoundException {
		CustomerRewards customerRewards = rewardsService.getCustomerRewards(customerId);
		if (customerRewards == null) {
			throw new CustomerNotFoundException("Customer Not Found.... ");
		}
		return ResponseEntity.ok(customerRewards);
	}

	@GetMapping("/rewards")
	public ResponseEntity<List<CustomerRewards>> getAllCustomerRewards() {
		List<CustomerRewards> customerRewardsList = rewardsService.getAllCustomerRewards();
		if (customerRewardsList.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(customerRewardsList);
	}

}
