/**
 * 
 */
package com.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author bappana
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rewards {

	private Integer year;
	private String month;
	private Integer points;

}
