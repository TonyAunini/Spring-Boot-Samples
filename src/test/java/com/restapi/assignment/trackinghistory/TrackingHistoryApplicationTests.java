package com.restapi.assignment.trackinghistory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class TrackingHistoryApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Test Case: Add First Value --> expected Result is Null
	@Test
	public void TestCase1() throws Exception {
		this.mockMvc.perform(get("/track/trackValue?input=test1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"output\":null}")));
	}

	// Test Case: Add Second Value (test2) --> expected Result is test1
	@Test
	public void TestCase2() throws Exception {
		this.mockMvc.perform(get("/track/trackValue?input=test2")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("\"output\":\"test1\"")));
	}

	// Test Case: Add Second Value (test3) --> expected Result is test2
	@Test
	public void TestCase3() throws Exception {
		this.mockMvc.perform(get("/track/trackValue?input=test3")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("\"output\":\"test2\"")));
	}

	// Test Case: Print track history --> expected Result is array order from Newest
	// to oldest
	@Test
	public void TestCase4() throws Exception {
		this.mockMvc.perform(get("/track/getHistory")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("[\"test3\",\"test2\",\"test1\"]")));
	}

	// Test Case: Call API without query string --> expected Result is BadRequest
	@Test
	public void TestCase5() throws Exception {
		this.mockMvc.perform(get("/track/trackValue")).andDo(print()).andExpect(status().isBadRequest());
	}

	// Test Case: Call API without query string (length of query string is more than
	// 1000) --> expected Result is BadRequest
	@Test
	public void TestCase6() throws Exception {
		String inputParam = "test";
		for (int i = 0; i < 1000; i++)
			inputParam += "a";
		this.mockMvc.perform(get("/track/trackValue?input=" + inputParam)).andDo(print())
				.andExpect(status().isBadRequest());

	}

	// Test Case: add more then 10 values , the history should return 10 only
	@Test
	public void TestCase7() throws Exception {

		for (int i = 4; i < 12; i++)
			this.mockMvc.perform(get("/track/trackValue?input=test" + i)).andExpect(status().isOk());

		this.mockMvc.perform(get("/track/getHistory")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(
						"[\"test11\",\"test10\",\"test9\",\"test8\",\"test7\",\"test6\",\"test5\",\"test4\",\"test3\",\"test2\"]")));
	}

}
