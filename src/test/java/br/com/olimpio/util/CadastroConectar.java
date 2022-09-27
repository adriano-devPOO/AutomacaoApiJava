package br.com.olimpio.util;

import org.openqa.selenium.WebDriver;

import br.com.olimpio.element.CadastroElementos;
import br.com.olimpio.element.CadastroPage;

public class CadastroConectar {
	
private final WebDriver driver;
	
	public CadastroConectar(WebDriver driver) {
		this.driver = driver;
	}

	protected CadastroElementos elementos() {
		return new CadastroElementos(driver);
	}
	
	public CadastroPage cadastroPage() {
		return new CadastroPage(driver);
	}
	
	public void navegar() {
		driver.get("file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html");
	}
	
}
