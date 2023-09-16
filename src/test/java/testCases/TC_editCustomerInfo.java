package testCases;

import java.io.IOException;
import java.util.function.Consumer;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CustomerInfo;
import pageObjects.Login;
import pageObjects.PageNavigation;
import resources.Base;
import resources.ReadConfig;

public class TC_editCustomerInfo extends Base {
	private WebDriver driver;
	private ReadConfig config;

	private void loginTest() {
		driver.get(config.getApplicationUrl());
		Login login = new Login(driver);
		login.setUserId(config.getUserID());
		login.setPassword(config.getPassword());
		login.clickSubmit();
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
			driver.close();
		}
	}

	@BeforeClass
	public void initialize() throws IOException {
		try {
			driver = initializeDriver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		config = readConfig;

		loginTest();
	}

	@Test(dataProvider = "editCustomersData")
	public void editCustomerInfo(String id, String address, String city, String state, String pin, String phone,
			String email) {

		PageNavigation navigate = new PageNavigation(driver);
		navigate.navigateToPage("Edit Customer");

		CustomerInfo customer = new CustomerInfo(driver);
		customer.enterCustomerId(id);
		customer.clickSubmitEditId();

		setIfNotNull(customer::setAddress, address);
		setIfNotNull(customer::setCity, city);
		setIfNotNull(customer::setState, state);
		setIfNotNull(customer::setPin, pin);
		setIfNotNull(customer::setMobNumber, phone);
		setIfNotNull(customer::setEmail, email);

		customer.clickSubmit();
		driver.switchTo().alert().accept();

	}

	private static <T> void setIfNotNull(Consumer<T> setter, T value) {
		if (value != null) {
			setter.accept(value);
		}
	}

	@DataProvider
	public Object[][] editCustomersData() {

		Object[][] data = new Object[1][7];
		data[0][0] = "42901";
		data[0][2] = "Florida";
		return data;
	}

}
