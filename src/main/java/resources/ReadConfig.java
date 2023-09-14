package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig() {
//		File src = new File(System.getProperty("user.dir")
//				+ "\\src\\main\\java\\test\\project\\OpenCart\\resources\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\testData\\config.properties");
			pro = new Properties();
			pro.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception is: " + e.getMessage());
		}

	}

	public String getBrowser() {
		return pro.getProperty("browser");
	}

	public String getApplicationUrl() {
		return pro.getProperty("url");
	}

	public String getUserID() {
		return pro.getProperty("userID");
	}

	public String getPassword() {
		return pro.getProperty("password");
	}

}
