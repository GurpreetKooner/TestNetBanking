package pageObjects;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerInfo {
	WebDriver driver;
	Logger log;

	public CustomerInfo(WebDriver driver, Logger log) {

		this.driver = driver;
		this.log = log;
		PageFactory.initElements(driver, this);

	}

	@FindBy(name = "name")
	WebElement customerName;

	@FindBy(name = "rad1")
	List<WebElement> genders;

	@FindBy(name = "dob")
	WebElement dob;

	@FindBy(name = "addr")
	WebElement address;

	@FindBy(name = "city")
	WebElement city;

	@FindBy(name = "state")
	WebElement state;

	@FindBy(name = "pinno")
	WebElement pin;

	@FindBy(name = "telephoneno")
	WebElement mobNumber;

	@FindBy(name = "emailid")
	WebElement email;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "sub")
	WebElement submit;

	@FindBy(xpath = "//p[text()='Customer Registered Successfully!!!']")
	WebElement successfullReg;

	@FindBy(xpath = "//td[text()='Customer ID']/following-sibling::td")
	WebElement customerId;

	@FindBy(name = "cusid")
	WebElement idEditCustomer;

	@FindBy(name = "AccSubmit")
	WebElement submitEditId;

	public void setCustomerName(String cName) {
		customerName.sendKeys(cName);
		log.info("Enter Customer Name: " + cName);

	}

	public void setGender(String cGender) {
		if (cGender.equalsIgnoreCase("male")) {
			genders.get(0).click();
		} else if (cGender.equalsIgnoreCase("female")) {
			genders.get(1).click();
		}
		log.info("Set gender to " + cGender);
	}

	public void setDOB(String cDob) {
		dob.sendKeys(cDob);
		log.info("Enter Customer DOB: " + cDob);
	}

	public void setAddress(String cAddress) {
		address.clear();
		address.sendKeys(cAddress);
		log.info("Enter Customer Address: " + cAddress);
	}

	public void setCity(String cCity) {
		city.clear();
		city.sendKeys(cCity);
		log.info("Enter Customer City: " + cCity);
	}

	public void setState(String cState) {
		state.clear();
		state.sendKeys(cState);
		log.info("Enter Customer State: " + cState);
	}

	public void setPin(String cPin) {
		pin.clear();
		pin.sendKeys(cPin);
		log.info("Enter Customer Pincode: " + cPin);
	}

	public void setMobNumber(String cMobNumber) {
		mobNumber.clear();
		mobNumber.sendKeys(cMobNumber);
		log.info("Enter Customer Phone number: " + cMobNumber);
	}

	public void setEmail(String cEmail) {
		email.clear();
		email.sendKeys(cEmail);
		log.info("Enter Customer Email: " + cEmail);
	}

	public void setPassword(String cPassword) {
		password.sendKeys(cPassword);
		log.info("Enter Customer Password: " + cPassword);
	}

	public void clickSubmit() {
		submit.click();
		log.info("Click Submit");
	}

	public boolean customerRegistered() {
		return successfullReg.isDisplayed();
	}

	public int getCustomerId() {
		String id = customerId.getText();
		log.info("Get Customer ID:" + id);
		return Integer.parseInt(id);
	}

	public void setNewCustomerInfo(String cName, String cGender, String cDob, String cAddress, String cCity,
			String cState, String cPin, String cPhone, String cEmail, String cPassword) {
		setCustomerName(cName);
		setGender(cGender);
		setDOB(cDob);
		setAddress(cAddress);
		setCity(cCity);
		setState(cState);
		setPin(cPin);
		setMobNumber(cPhone);
		setEmail(cEmail);
		setPassword(cPassword);
		clickSubmit();
	}

	public void enterCustomerId(String id) {
		idEditCustomer.sendKeys(id);
		log.info("Enter Customer Id to edit:" + id);
	}

	public void clickSubmitEditId() {
		submitEditId.click();
		log.info("Click Submit");
	}

}
