package Tests;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProfileTest extends BasicTest {

	@Test(priority = 0)
    public void editProfileTest() {
  	  SoftAssert sa = new SoftAssert();
	  driver.navigate().to(this.baseUrl + "/guest-user/login-form");
	  locationPopupPageElements.closePopupWindow();
	  loginPageElements.login(this.email, this.password);
	  sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Login Successfull"), "[ERROR] Login is not successful");
	  		  
	  driver.navigate().to(baseUrl + "/member/profile");
	  profilePageElements.changePersonalInformations("Alesandar", "Petrovic", "Obrenoviceva 34/2a", "0624895136", "18000", "United States", "Malaga", "Coin");
	  sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Setup Successful"), "[ERROR] Profile informations are not saved");
	 		  
	  authPageElements.logout();
	  sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Logout Successfull!"), "[ERROR] Login is not successful");
}
	
	@Test(priority = 10)
	public void changeProfileImageTest() throws IOException, InterruptedException {
		SoftAssert sa = new SoftAssert();
	    driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		locationPopupPageElements.closePopupWindow();
		loginPageElements.login(this.email, this.password);

		driver.navigate().to(baseUrl + "/member/profile");
		String imgPath = new File("img/image.png").getCanonicalPath();                
		profilePageElements.uploadPicture(imgPath);
		sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Profile Image Uploaded Successfully"), "[ERROR] Profile image did not upload successfully");
		if (notificationSystemPageElements.NotificationNotActive()) {
			profilePageElements.deleteProfilePhoto();
		}
		sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Profile Image Deleted Successfully"), "[ERROR] Profile image was not deleted successfully");
		if (notificationSystemPageElements.NotificationNotActive()) {
			Thread.sleep(3000);
			authPageElements.logout();
			sa.assertTrue(notificationSystemPageElements.getMessageText().contains("Logout Successfull!"), "[ERROR] Login is not successful");
		}
	}
	
}
