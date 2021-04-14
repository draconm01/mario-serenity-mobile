package tests;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)  
public class WebAvivaTest {

	@Managed()
	WebDriver driver;

	@Before
	public void avant_Test() {

		String browserName = driver.toString();

		if(browserName.contains("chrome")) {
			//****** PageLoadStrategy (chrome) ***********
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(chromeOptions);

		} else if(browserName.contains("firefox")) {
			//****** PageLoadStrategy (firefox) ***********
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new FirefoxDriver(firefoxOptions);
		}
	}

	@Test
	public void Web_Aviva_Test() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://pprd.aviva.fr");
		driver.manage().window().maximize();

		WebElement btnAccepterCookies = driver.findElement(By.xpath("//*[contains(text(),'Accepter')]"));
		waitIsVisible(btnAccepterCookies);
		btnAccepterCookies.click();
		waitIsInvisible(btnAccepterCookies);

		WebElement btnDevisHabitation = driver.findElement(By.xpath("//*[@id=\"section-menu-six-cards\"]/div/div/div[2]/div[2]/div[2]/p[1]/a"));
		btnDevisHabitation.click();

		WebElement typeLogementAutresBiens = driver.findElement(By.xpath("//span[contains(text(),'Autres biens')]/ancestor::span"));
		waitIsVisible(typeLogementAutresBiens);
		typeLogementAutresBiens.click();
		//sleep(2);

		WebElement residencePrincipale = driver.findElement(By.xpath("//*[contains(text(),'Ma résidence principale')]"));
		waitIsClickable(residencePrincipale);
		residencePrincipale.click();
		//sleep(2);
		//waitLoaderAbsent();

		WebElement proprietaire = driver.findElement(By.xpath("//*[contains(text(),'Propriétaire')]"));
		proprietaire.click();
		//sleep(2);
		//waitLoaderAbsent();

		WebElement nonLocationSaisonniere = driver.findElement(By.xpath("//span[contains(text(),'NON')]"));
		nonLocationSaisonniere.click();

		WebElement poursuivre = driver.findElement(By.xpath("//*[@value='Poursuivre']"));
		poursuivre.click();

		sleep(3);

		WebElement messageRouge = driver.findElement(By.xpath("//*[contains(text(),\"Nous ne pouvons malheureusement pas vous proposer de tarif habitation en ligne.\")]"));
		hightlight(messageRouge);
		assertTrue(messageRouge.isDisplayed());

		sleep(3);

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
	
	public void waitIsVisible(By locator) {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(locator));
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

	public void sleep(int seconds) {
		String stringSeconds = String.valueOf(seconds);
		seconds = Integer.parseInt(stringSeconds+"000");
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
