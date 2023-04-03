/**
 * 
 */
package com.rewards.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rewards.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bappana
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRewards {

	@JsonProperty(value = "Customer", index = 1)
	private Customer customer;
	private int totalRewardPoints;
	@JsonProperty(value = "Rewards")
	private List<Rewards> rewards;

}
