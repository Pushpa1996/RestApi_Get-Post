package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBse;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBse {
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

	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {

		RestClient = new RestClient();
		closeableHttpResponse = RestClient.get(URL);

		// a.Status Code

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, 200, "Testcase passed");

		// b. Json String:
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responsejson = new JSONObject(responseString);

		// perpage
		String perpage = TestUtil.getValueByJPath(responsejson, "/per_page");

		Assert.assertEquals(Integer.parseInt(perpage), 6, "Testcase passed");

		// get the value from JSON ARRAY:
		String lastNameFirstIndex = TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
		Assert.assertEquals(lastNameFirstIndex, "Lawson", "Testcase passed");

		String lastNameSecondIndex = TestUtil.getValueByJPath(responsejson, "/data[1]/last_name");
		Assert.assertEquals(lastNameSecondIndex, "Ferguson", "Testcase passed");

		// c. All Headers

		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array-->" + allHeaders.toString());
	}

	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {

		RestClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		closeableHttpResponse = RestClient.get(URL, headerMap);
		// a.Status Code

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, 200, "Testcase passed");

		// b. Json String:
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responsejson = new JSONObject(responseString);

		// perpage
		String perpage = TestUtil.getValueByJPath(responsejson, "/per_page");
		Assert.assertEquals(Integer.parseInt(perpage), 6, "Testcase passed");

		// get the value from JSON ARRAY:
		String lastNameFirstIndex = TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
		Assert.assertEquals(lastNameFirstIndex, "Lawson", "Testcase passed");

		String lastNameSecondIndex = TestUtil.getValueByJPath(responsejson, "/data[1]/last_name");
		Assert.assertEquals(lastNameSecondIndex, "Ferguson", "Testcase passed");

	}

}
