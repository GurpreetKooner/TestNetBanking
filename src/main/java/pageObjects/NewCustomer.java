package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewCustomer {
	WebDriver driver;

	public NewCustomer(WebDriver driver) {

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
		address.sendKeys(cAddress);
	}

	public void setCity(String cCity) {
		city.sendKeys(cCity);
	}

	public void setState(String cState) {
		state.sendKeys(cState);
	}

	public void setPin(String cPin) {
		pin.sendKeys(cPin);
	}

	public void setMobNumber(String cMobNumber) {
		mobNumber.sendKeys(cMobNumber);
	}
	
	public void setEmail(String cEmail) {
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

}
