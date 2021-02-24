package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test (priority = 0)
	public void addMealToCartTest() throws InterruptedException {
        SoftAssert sa = new SoftAssert();
		driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPageElements.closePopupWindow();
		mealPageElements.addToCart("3");
		sa.assertTrue(notificationSystemPageElements.getMessageText().contains("The Following Errors Occurred"), "[ERROR] Location is already selected");
		if (notificationSystemPageElements.NotificationNotActive()) {
			locationPopupPageElements.getPopupLocationWindow();
			locationPopupPageElements.setLocation("City Center - Albany");
			mealPageElements.addToCart("3");
			sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Meal Added To Cart"),
					"[ERROR] Meal is not added to cart");
		}
	}
	
	
	@Test(priority = 10)
	public void addMealToFavoritesTest() {
		SoftAssert sa = new SoftAssert();
		driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPageElements.closePopupWindow();
		mealPageElements.addToFavourites();
		sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Please login first!"),
				"[ERROR] User already logged in");
		driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		loginPageElements.login(this.email, this.password);
		driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPageElements.addToFavourites();
		sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Product has been added to your favorites"), "[ERROR] Meal is not added to favorites");
	}
	
	
	@Test(priority = 20)
	public void clearCartTest() throws IOException, InterruptedException  {
		driver.navigate().to(this.baseUrl + "/meals");
		locationPopupPageElements.setLocation("City Center - Albany");
		SoftAssert sa = new SoftAssert();
        File file = new File("data/Data.xlsx");
		
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = wb.getSheet("Meals");
		
		for (int i = 1; i < 6; i++) {
			XSSFRow row = sheet.getRow(i);
			
			XSSFCell link = row.getCell(0);
			XSSFCell quantity = row.getCell(1);
			
			String linkS = link.getStringCellValue();
			double quantityD = quantity.getNumericCellValue();
			int quantityI = (int) quantityD;
			String quantityS = String.valueOf(quantityI);
			
			driver.navigate().to(linkS);
			mealPageElements.addToCart(quantityS);
					
			sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Meal Added To Cart"), "[ERROR] Meal is not added to cart");
		}
		cartSummaryPageElements.clearAll();
		sa.assertTrue(notificationSystemPageElements.getMessageText().contains("All meals removed from Cart successfully"), "[ERROR] cart is not empty");
		sa.assertAll();
			fis.close();
			wb.close();
		}
}
