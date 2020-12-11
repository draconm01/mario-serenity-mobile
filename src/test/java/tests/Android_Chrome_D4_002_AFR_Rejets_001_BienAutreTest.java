package tests;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)  
public class Android_Chrome_D4_002_AFR_Rejets_001_BienAutreTest {

	public static final String USERNAME = "mariodracon1";
	public static final String AUTOMATE_KEY = "NygFxGNb598rs1xrsjQ4";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@Managed()
	WebDriver driver;


	@Test
	public void name() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("browserName", "chrome");
		caps.setCapability("device", "Google Pixel 3");
		caps.setCapability("realMobile", "true");
		caps.setCapability("os_version", "9.0");
		caps.setCapability("name", "Android test");

		driver = new RemoteWebDriver(new URL(URL), caps);

		/* Write your Custom code here */
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("http://pprd.aviva.fr");

		WebElement btnAccepterCookies = driver.findElement(By.xpath("//*[contains(text(),'Accepter')]"));
		waitIsVisible(btnAccepterCookies);
		waitIsClickable(btnAccepterCookies);
		btnAccepterCookies.click();
		waitIsInvisible(btnAccepterCookies);

		WebElement btnDevisHabitation = driver.findElement(By.xpath("//*[@id=\"section-menu-six-cards\"]/div/div/div[2]/div[2]/div[2]/p[1]/a"));
		btnDevisHabitation.click();

		WebElement typeLogementAutresBiens = driver.findElement(By.xpath("//span[contains(text(),'Autres biens')]/ancestor::span"));
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", typeLogementAutresBiens);
		typeLogementAutresBiens.click();
		waitLoaderAbsent();

		WebElement residencePrincipale = driver.findElement(By.xpath("//*[contains(text(),'Ma résidence principale')]"));
		residencePrincipale.click();
		waitLoaderAbsent();

		WebElement proprietaire = driver.findElement(By.xpath("//*[contains(text(),'Propriétaire')]"));
		proprietaire.click();
		waitLoaderAbsent();

		WebElement nonLocationSaisonniere = driver.findElement(By.xpath("//span[contains(text(),'NON')]"));
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", nonLocationSaisonniere);
		nonLocationSaisonniere.click();

		WebElement poursuivre = driver.findElement(By.xpath("//*[@value='Poursuivre']"));
		poursuivre.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement messageRouge = driver.findElement(By.xpath("//*[contains(text(),\"Nous ne pouvons malheureusement pas vous proposer de tarif habitation en ligne.\")]"));
		hightlight(messageRouge);
		assertTrue(messageRouge.isDisplayed());

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.close();
		driver.quit();
	}

	/**
	 * Méthodes utilitaires
	 */
	public void waitLoaderAbsent() {
		WebElement loader = driver.findElement(By.xpath("//*[contains(text(),\"Veuillez patienter\")]"));
		new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(loader));
	}

	public void waitIsClickable(WebElement webElement) {
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public void waitIsVisible(WebElement webElement) {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitIsInvisible(WebElement webElement) {
		new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(webElement));
	}

	// Draws a red border around the found element. Does not set it back anyhow.
	public void hightlight(WebElement webElement) {
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", webElement);
		}
	}

	public void scrollIntoView(WebElement webElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
