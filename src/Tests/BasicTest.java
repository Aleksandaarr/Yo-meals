package Tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import Pages.AuthPage;
import Pages.CartSummaryPage;
import Pages.LocationPopupPage;
import Pages.LoginPage;
import Pages.MealPage;
import Pages.NotificationSystemPage;
import Pages.ProfilePage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected String email;
	protected String password;
	protected String baseUrl;
	protected AuthPage authPageElements;
	protected CartSummaryPage cartSummaryPageElements;
	protected LocationPopupPage locationPopupPageElements;
	protected LoginPage loginPageElements;
	protected MealPage mealPageElements;
	protected NotificationSystemPage notificationSystemPageElements;
	protected ProfilePage profilePageElements;
	

	@BeforeClass
	public void setup() throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver_lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.waiter = new WebDriverWait(driver, 30);
		this.js = (JavascriptExecutor) driver;
		this.authPageElements = new AuthPage(driver, waiter, js);
		this.cartSummaryPageElements = new CartSummaryPage(driver, waiter, js);
		this.locationPopupPageElements = new LocationPopupPage(driver, waiter, js);
		this.loginPageElements = new LoginPage(driver, waiter, js);
		this.mealPageElements = new MealPage(driver, waiter, js);
		this.notificationSystemPageElements = new NotificationSystemPage(driver, waiter, js);
		this.profilePageElements = new ProfilePage(driver, waiter, js);
		this.baseUrl = "http://demo.yo-meals.com";
		this.email = "customer@dummyid.com";
		this.password = "12345678a";
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterTestScreenshot(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {                         

			Date d = new Date();
			System.out.println(d.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile, new File("screenshots/screenshot" + sdf.format(d) + ".png"));

		} else {
			System.out.println("Successfull test");
		}

		this.driver.manage().deleteAllCookies();
	}

	@AfterClass
	public void exit() {
		this.driver.quit();
	}

}