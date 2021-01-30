package test.selenium;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAttractionViewSortByDistance {

	
	@Test
	public void testHeight() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/SpeedyFila/");
		
		driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div[2]/div/div[2]/form/div[2]/input")).click();
		driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div[2]/div/div[2]/form/input")).click();
		
		Thread.sleep(1000);
		
		List<WebElement> listOfWebElement = driver.findElements(By.xpath("//*[@id=\"attractionView\"]/div/div[2]/div/div/div[6]/h4"));
		String result = listOfWebElement.get(0).getText();
		String result2 = listOfWebElement.get(1).getText();
		
		driver.close();
		
		int distance1 = Integer.parseInt(result);
		int distance2 = Integer.parseInt(result2);
		
		//verify if distance2 > distance1, so the list of attractions are correctly sorted 
		boolean expression = (distance2 - distance1)>=0;
		
		assertTrue(expression);
		
	}
}
