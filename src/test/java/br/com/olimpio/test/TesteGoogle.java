package br.com.olimpio.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	
	private WebDriver driver;

	@Test
	public void testeGoogle() {
		
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 765));
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com.br/");
		
		Assert.assertEquals("Google", driver.getTitle());
		
	}
	
	@After
	public void fecharBrowser() {
		driver.quit();
	}
}
