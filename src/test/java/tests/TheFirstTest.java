package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)  
public class TheFirstTest {

	public static final String USERNAME = "mariodracon1";
	public static final String AUTOMATE_KEY = "NygFxGNb598rs1xrsjQ4";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@Managed()
	WebDriver driver;

	@Test
	public void name() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();

		//		//APP MOBILE//***************************************
		// Set your access credentials
		caps.setCapability("browserstack.user", "mariodracon1");
		caps.setCapability("browserstack.key", "NygFxGNb598rs1xrsjQ4");

		// Set URL of the application under test
		caps.setCapability("app", "bs://81feb57c3bff5cfc646f94b270b4dc535e226081");

		// Specify device and os_version for testing
		caps.setCapability("device", "Google Pixel 3");
		caps.setCapability("os_version", "9.0");
//		// Specify device and os_version for testing
//		caps.setCapability("device", "iPhone XS");
//		caps.setCapability("os_version", "12");


		// Set other BrowserStack capabilities
		caps.setCapability("project", "First Java Project");
		caps.setCapability("build", "Java Android");
		caps.setCapability("name", "Firefox Mario Test");

		// Initialise the remote Webdriver using BrowserStack remote URL
		// and desired capabilities defined above
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
				new URL("http://hub.browserstack.com/wd/hub"), caps);
		/* Write your Custom code here */
		
		
		// Invoke driver.quit() after the test is done to indicate that the test is completed.
		driver.closeApp();
		driver.quit();


		//		//BROWSER MOBILE//***************************************
		//		caps.setCapability("browserName", "android");
		//		caps.setCapability("device", "Google Pixel 3");
		//		caps.setCapability("realMobile", "true");
		//		caps.setCapability("os_version", "9.0");
		//		caps.setCapability("name", "mariodracon1's First Test");
		//		
		//		driver = new RemoteWebDriver(new URL(URL), caps);
		//
		//		/* Write your Custom code here */
		//		driver.get("http://rect.aviva.fr");
		//		
		//		System.out.println("Hello world !");
		//
		//		// Invoke driver.quit() after the test is done to indicate that the test is completed.
		//		driver.quit();
		//		
	}
}
