package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class PageNavigation {

	private WebDriver driver;

	public PageNavigation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private final Map<String, By> pageLinks = new HashMap<>();

	{
		pageLinks.put("Manager", By.xpath("//ul[@class='menusubnav']//a[text()='Manager']"));
		pageLinks.put("New Customer", By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']"));
		pageLinks.put("Edit Customer", By.xpath("//ul[@class='menusubnav']//a[text()='Edit Customer']"));
		pageLinks.put("Delete Customer", By.xpath("//ul[@class='menusubnav']//a[text()='Delete Customer']"));
		pageLinks.put("New Account", By.xpath("//ul[@class='menusubnav']//a[text()='New Account']"));
		pageLinks.put("Edit Account", By.xpath("//ul[@class='menusubnav']//a[text()='Edit Account']"));
		pageLinks.put("Delete Account", By.xpath("//ul[@class='menusubnav']//a[text()='Delete Account']"));
		pageLinks.put("Deposit", By.xpath("//ul[@class='menusubnav']//a[text()='Deposit']"));
		pageLinks.put("Withdrawal", By.xpath("//ul[@class='menusubnav']//a[text()='Withdrawal']"));
		pageLinks.put("Fund Transfer", By.xpath("//ul[@class='menusubnav']//a[text()='Fund Transfer']"));
		pageLinks.put("Change Password", By.xpath("//ul[@class='menusubnav']//a[text()='Change Password']"));
		pageLinks.put("Balance Enquiry", By.xpath("//ul[@class='menusubnav']//a[text()='Balance Enquiry']"));
		pageLinks.put("Mini Statement", By.xpath("//ul[@class='menusubnav']//a[text()='Mini Statement']"));
		pageLinks.put("Customised Statement", By.xpath("//ul[@class='menusubnav']//a[text()='Customised Statement']"));
		pageLinks.put("Log out", By.xpath("//ul[@class='menusubnav']//a[text()='Log out']"));
	}

	@FindBy(xpath = "//div[@id='dismiss-button']")
	private WebElement ad1;

	@FindBy(xpath = "//div[contains(@class,'close-button')]")
	private WebElement ad2;

	// Add other WebElement fields here

	public void navigateToPage(String pageName) {
		WebElement pageLink = driver.findElement(pageLinks.get(pageName));
		pageLink.click();
	}

	public void closeAd() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		if (ad1.isDisplayed()) {

			System.out.println("-----------###################################################################");

			jse.executeScript("arguments[0].click()", ad1);
//			ad.click();
		} else if (ad2.isDisplayed()) {
			System.out.println("-----------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			jse.executeScript("arguments[0].click()", ad2);
		}

	}
}
