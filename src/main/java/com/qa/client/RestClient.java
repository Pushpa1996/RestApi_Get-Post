package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.HTTP;

public class RestClient {

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient HttpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse HttpResponse = HttpClient.execute(httpget);

		return HttpResponse;

	}

	// 2. GET Method with Headers:
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient HttpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {

			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse HttpResponse = HttpClient.execute(httpget);

		return HttpResponse;

	}
	//3. POST Method:
	public CloseableHttpResponse post(String URL, String Entity, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient Httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(URL);
		httppost.setEntity(new StringEntity(Entity));
		
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			
			httppost.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse HttpResponse = Httpclient.execute(httppost);

		return HttpResponse;

	}

}
