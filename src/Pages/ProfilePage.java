package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage{

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}
	
	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}
	
	public WebElement getEmail() {
		return this.driver.findElement(By.name("user_email"));
	}
	
	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}
	
	public WebElement getPhoneNumber() {
		return this.driver.findElement(By.name("user_phone"));
	}
	
	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}
	
	public Select getCountry() {
		WebElement selectButton = this.driver.findElement(By.name("user_country_id"));
		Select countrySelect = new Select(selectButton);
		return countrySelect;
	}
	
	public Select getState() {
		WebElement selectButton = this.driver.findElement(By.name("user_state_id"));
		Select stateSelect = new Select(selectButton);
		return stateSelect;
	}
	
	public Select getCity() {
		WebElement selectButton = this.driver.findElement(By.name("user_city"));
		Select citySelect = new Select(selectButton);
		return citySelect;
	}

	public WebElement getSavePersonalInformations() {
		return this.driver.findElement(By.xpath("//*[@class=\"row\"][5]//input"));
	}
	
	public WebElement getUploadButton() {
		return this.driver.findElement(By.xpath("//a[@title=\"Uplaod\"]"));
	}

	public WebElement getRemoveButton() {
		return this.driver.findElement(By.xpath("//a[@title=\"Remove\"]"));
		
	}
	
	public WebElement getUpload() {
		return this.driver.findElement(By.xpath("//*[@type=\"file\"]"));
	}
	public void uploadPicture(String picture) {
		this.js.executeScript("arguments[0].click();", this.getUploadButton());
		this.getUpload().sendKeys(picture);
	}	
	
	public void deleteProfilePhoto () {
		this.js.executeScript("arguments[0].click();", this.getRemoveButton());
	}
	
	public void changePersonalInformations(String firstName, String lastName, String address, String phoneNo, String zipCode, String country, String state, String city) {
		this.getFirstName().sendKeys(Keys.chord(Keys.CONTROL, "a"), firstName);
		this.getLastName().sendKeys(Keys.chord(Keys.CONTROL, "a"), lastName);
		this.getAddress().sendKeys(Keys.chord(Keys.CONTROL, "a"), address);
		this.getPhoneNumber().sendKeys(Keys.chord(Keys.CONTROL, "a"), phoneNo);
		this.getZipCode().sendKeys(Keys.chord(Keys.CONTROL, "a"), zipCode);
		this.getCountry().selectByVisibleText(country);
		this.getState().selectByVisibleText(state);
		this.getCity().selectByVisibleText(city);
		this.getSavePersonalInformations().click();
	
	}
}
