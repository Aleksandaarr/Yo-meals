package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage{

	public AuthPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getProfileNameButton() {
		return this.driver.findElement(By.xpath("//li[@class=\"filled \"]/a"));
	}

	public WebElement getMyAccountButton() {
		return this.driver.findElement(By.linkText("My Account"));
	}
	
	public WebElement getLogoutButton() {
		return this.driver.findElement(By.linkText("Logout"));
	}
	
	public void logout() {
		this.getProfileNameButton().click();
		this.getLogoutButton().click();
	}
}
