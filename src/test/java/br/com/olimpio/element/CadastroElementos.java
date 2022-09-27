package br.com.olimpio.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CadastroElementos {

	private final WebDriver driver;

	public CadastroElementos(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement elementXpath(By xpath) {
		return driver.findElement(xpath);
	}

	public WebElement elementId(By id) {
		return driver.findElement(id);
	}

	public WebElement linkVoltar(By linkText) {
		return driver.findElement(linkText);
	}

	public WebElement elementTagName(By text) {
		return driver.findElement(text);
	}

	public WebDriver getDriver() {
		return driver;
	}

}
