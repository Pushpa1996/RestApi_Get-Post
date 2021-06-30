package com.qa.tests;

import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import com.qa.base.TestBse;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest {
	TestBse TestBse;
	String URL;
	RestClient RestClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeTest
	public void setup() throws IOException {
		TestBse = new TestBse();

		String ServiceUrl1 = TestBse.Base("ServiceUrl");

		String ApiURL1 = TestBse.Base("ApiURL");

		URL = ServiceUrl1 + ApiURL1;

	}

	@Test
	public void postAPITest() throws ParseException, IOException {
		RestClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API:
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("PUSHPA", "TESTER"); // expected users obejct

		// object to json file:
		mapper.writeValue(new File("C:\\Users\\pushpa.d.kumari\\eclipse-workspace\\RestApiDemo\\postuser.json"), users);

		// java object to json in String:
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);

		closeableHttpResponse = RestClient.post(URL, usersJsonString, headerMap); // call the API

		// validate response from API:
		// 1. status code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, TestBse.RESPONSE_STATUS_CODE_201);

		// 2. JsonString:
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		Users usersResObj = mapper.readValue(responseString, Users.class); // actual users object
		System.out.println(usersResObj);

		Assert.assertTrue(users.getName().equals(usersResObj.getName()));

		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));

		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());

	}

}