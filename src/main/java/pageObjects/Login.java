package pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	WebDriver driver;
	Logger log;

	public Login(WebDriver driver, Logger log) {

		this.driver = driver;
		this.log = log;
		PageFactory.initElements(driver, this);

	}

	@FindBy(name = "uid")
	@CacheLookup
	WebElement txtUserId;

	@FindBy(name = "password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(name = "btnLogin")
	@CacheLookup
	WebElement btnLogin;

	public void setUserId(String uname) {
		txtUserId.sendKeys(uname);
		log.info("Enter Login User ID: " + uname);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
		log.info("Enter Login Password: " + pwd);
	}

	public void clickSubmit() {
		btnLogin.click();
		log.info("Click Login Submit");
	}
}
