package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getAddToCartButton() {
		return this.driver.findElement(By.xpath("//*[@class=\"product-description\"]/div[3]/div[2]/a"));
	}
	public WebElement getQuantity() {
		return this.driver.findElement(By.name("product_qty"));
	}

	public void addToCart(String quantity) throws InterruptedException {
		this.getQuantity().sendKeys(Keys.chord(Keys.CONTROL, "a"), quantity);
		Thread.sleep(3000);
		this.getAddToCartButton().click();
	}

	public void addToFavourites() {
		this.driver.findElement(By.className("favourite")).click();
	}
}
