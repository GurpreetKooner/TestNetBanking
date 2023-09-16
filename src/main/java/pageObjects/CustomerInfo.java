package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerInfo {
	WebDriver driver;

	public CustomerInfo(WebDriver driver) {

		this.driver = driver;
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
		
	}

	public void setGender(String cGender) {
		if (cGender.equalsIgnoreCase("male")) {
			genders.get(0).click();
		} else if (cGender.equalsIgnoreCase("female")) {
			genders.get(1).click();
		}
	}

	public void setDOB(String cDob) {
		dob.sendKeys(cDob);
	}

	public void setAddress(String cAddress) {
		address.clear();
		address.sendKeys(cAddress);
	}

	public void setCity(String cCity) {
		city.clear();
		city.sendKeys(cCity);
	}

	public void setState(String cState) {
		state.clear();
		state.sendKeys(cState);
	}

	public void setPin(String cPin) {
		pin.clear();
		pin.sendKeys(cPin);
	}

	public void setMobNumber(String cMobNumber) {
		mobNumber.clear();
		mobNumber.sendKeys(cMobNumber);
	}

	public void setEmail(String cEmail) {
		email.clear();
		email.sendKeys(cEmail);
	}

	public void setPassword(String cPassword) {
		password.sendKeys(cPassword);
	}

	public void clickSubmit() {
		submit.click();
	}

	public boolean customerRegistered() {
		return successfullReg.isDisplayed();
	}

	public int getCustomerId() {
		String id = customerId.getText();
		return Integer.parseInt(id);
	}

	public void setNewCustomerInfo(String cName, String cGender, String cDob, String cAddress, String cCity,
			String cState, String cPin, String cPphone, String cEmail, String cPassword) {
		customerName.sendKeys(cName);
		if (cGender.equalsIgnoreCase("male")) {
			genders.get(0).click();
		} else if (cGender.equalsIgnoreCase("female")) {
			genders.get(1).click();
		}
		dob.sendKeys(cDob);
		address.sendKeys(cAddress);
		city.sendKeys(cCity);
		state.sendKeys(cState);
		pin.sendKeys(cPin);
		mobNumber.sendKeys(cPphone);
		email.sendKeys(cEmail);
		password.sendKeys(cPassword);
		submit.click();
	}

	public void enterCustomerId(String id) {
		
		idEditCustomer.sendKeys(id);
	}

	public void clickSubmitEditId() {

		submitEditId.click();

	}

}
