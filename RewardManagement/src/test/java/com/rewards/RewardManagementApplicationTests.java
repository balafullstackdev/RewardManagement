package com.rewards;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.rewards.entity.Customer;
import com.rewards.model.CustomerRewards;
import com.rewards.service.RewardsService;

@SpringBootTest
@AutoConfigureMockMvc
class RewardManagementApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RewardsService rewardsService;

	@Test
	public void testRestControllerGetCustomerRewards() throws Exception {

		CustomerRewards customerRewards = new CustomerRewards();
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("Bala");
		customerRewards.setCustomer(customer);
		customerRewards.setTotalRewardPoints(90);
		
		when(rewardsService.getCustomerRewards(1L)).thenReturn(customerRewards);
		mockMvc.perform(get("/customers/1/rewards")).andExpect(status().isOk())
				.andExpect(jsonPath("$.totalRewardPoints").value("90"));

	}

}
