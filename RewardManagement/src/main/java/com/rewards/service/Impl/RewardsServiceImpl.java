/**
 * 
 */
package com.rewards.service.Impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.model.CustomerRewards;
import com.rewards.model.Rewards;
import com.rewards.repository.CustomerRepository;
import com.rewards.repository.TransactionRepository;
import com.rewards.service.RewardsService;

/**
 * @author bappana
 *
 */
@Service
public class RewardsServiceImpl implements RewardsService {

	@Value("${rewards.months}")
	private Integer rewardMonths;
	@Value("${rewards.eligible-amount}")
	private BigDecimal rewardEligibleAmount;
	@Value("${rewards.bonus-eligible-amount}")
	private BigDecimal bonusRewardEligibleAmount;
	@Value("${rewards.points-per-dollar}")
	private BigDecimal pointsPerDollar;
	@Value("${rewards.points-per-dollar.overMax}")
	private BigDecimal pointsPerDollarOverMax;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerRewards getCustomerRewards(Long customerId) {
		Customer customer = customerRepository.findByCustomerId(customerId);
		return this.getComputedRewards(customer);
	}

	@Override
	public List<CustomerRewards> getAllCustomerRewards() {
		List<CustomerRewards> customerRewards = new ArrayList<CustomerRewards>();
		Iterable<Customer> customers = customerRepository.findAll();
		customers.forEach(customer -> customerRewards.add(this.getComputedRewards(customer)));
		return customerRewards;
	}

	/**
	 * This method return CustomerRewards Object for given Customer
	 * 
	 * @param customerId
	 * @param retailerId
	 * @return
	 */
	private CustomerRewards getComputedRewards(Customer customer) {

		if (customer == null) {
			return null;
		}
		List<Rewards> rewardsList = new ArrayList<>();
		int totalPoints = 0;
		for (int i = 1; i <= rewardMonths; i++) {
			// Get the current date
			LocalDate currentDate = LocalDate.now();

			// Get the Last month first and last day
			LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(i).withDayOfMonth(1);
			LocalDate lastDayOfPreviousMonth = firstDayOfPreviousMonth.plusMonths(1).minusDays(1);

			// Retrieve all the Transactions associated to the given customer within the
			// range
			List<Transaction> transactions = transactionRepository.findAllByCustomerIdAndUpdatedDtBetween(
					customer.getCustomerId(), Date.valueOf(firstDayOfPreviousMonth),
					Date.valueOf(lastDayOfPreviousMonth));

			// Calculate Rewards based on the rules given
			int rewardsPoints = transactions.stream().map(transaction -> this.calculateRewardPoints(transaction))
					.collect(Collectors.summingInt(reward -> reward.intValue()));

			rewardsList.add(new Rewards(firstDayOfPreviousMonth.getYear(),
					firstDayOfPreviousMonth.getMonth().name().toLowerCase(), rewardsPoints));
			totalPoints += rewardsPoints;

		}
		CustomerRewards customerRewards = new CustomerRewards(customer, totalPoints, rewardsList);
		return customerRewards;
	}

	/**
	 * This method calculates rewards for give rules/properties in Configuration
	 * 
	 * @param transaction
	 * @return
	 */
	private Long calculateRewardPoints(Transaction transaction) {
		BigDecimal rewardedPointsForTransaction = BigDecimal.ZERO;
		BigDecimal amount = transaction.getAmount();
		if (amount.compareTo(bonusRewardEligibleAmount) > 0) {
			BigDecimal bonusRewardAmount = amount.subtract(bonusRewardEligibleAmount);
			BigDecimal bonusRewardPoints = bonusRewardAmount.multiply(pointsPerDollarOverMax);

			BigDecimal rewardAmount = bonusRewardEligibleAmount.subtract(rewardEligibleAmount);
			BigDecimal rewardPoints = rewardAmount.multiply(pointsPerDollar);
			rewardedPointsForTransaction = bonusRewardPoints.add(rewardPoints);
		} else if (amount.compareTo(rewardEligibleAmount) > 0) {
			BigDecimal rewardAmount = amount.subtract(rewardEligibleAmount);
			BigDecimal rewardPoints = rewardAmount.multiply(pointsPerDollar);
			rewardedPointsForTransaction = rewardPoints;
		}
		return rewardedPointsForTransaction.setScale(0, RoundingMode.HALF_UP).longValueExact();
	}

	

}
