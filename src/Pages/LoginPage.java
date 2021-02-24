package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getEmail() {
		return this.driver.findElement(By.name("username"));
	}
	
	public WebElement getPassword() {
		return this.driver.findElement(By.name("password"));
	}
	
	public WebElement getSubmit() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public void login(String email, String password) {
		this.getEmail().sendKeys(Keys.chord(Keys.CONTROL, "a") ,email);
		this.getPassword().sendKeys(Keys.chord(Keys.CONTROL, "a") ,password);
		this.getSubmit().click();
	}
}
