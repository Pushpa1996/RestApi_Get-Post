package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBse {
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public Properties prop;

	public String Base(String Key) throws IOException {

		prop = new Properties();
		File file = new File(".\\src\\main\\java\\com\\qa\\config\\congig.properties");
		FileInputStream ip = new FileInputStream(file);
		prop.load(ip);
		return prop.getProperty(Key);

	}

}
