package br.com.olimpio.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

//import br.com.olimpio.usuario.Usuario;
import br.com.olimpio.util.CadastroConectar;

@RunWith(Parameterized.class)
public class CadastroCamposObrigatoriosTeste {
	private static WebDriver driver;
	private static CadastroConectar conectarCadastro;
	public static WebDriverWait wait;
	
	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobrenome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter (value = 3)
    public String escolaridade;
	@Parameter(value = 4)
	public List<String> comidas;
	@Parameter(value = 5)
	public List<String> esportes;
	@Parameter (value = 6)
	public String msg;

	@BeforeClass
	public static void inicio() {
		driver = new ChromeDriver();
		conectarCadastro = new CadastroConectar(driver);
		wait = new WebDriverWait(driver, 20);
	}

	@AfterClass
	public static void limparConexao() {
		driver.quit();
	}

	@Parameters
	public static Collection<Object[]> getColecao(){
		return Arrays.asList(new Object[][] {
			{"", "", "", "", Arrays.asList(), Arrays.asList(), "Nome eh obrigatorio"},
			{"Adriano", "", "", "", Arrays.asList(), Arrays.asList(), "Sobrenome eh obrigatorio"},
			{"Adriano", "Olimpio", "", "", Arrays.asList(), Arrays.asList(), "Sexo eh obrigatorio"},
			{"Adriano", "Olimpio", "Masculino", "1o grau incompleto", Arrays.asList("Carne", "Vegetariano"), Arrays.asList(), "Tem certeza que voce eh vegetariano?"},
			{"Adriano", "Olimpio", "Masculino", "1o grau incompleto", Arrays.asList("Carne", "Pizza"), Arrays.asList("Karate", "O que eh esporte?"), "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void validarCamposObrigatorios() {
		conectarCadastro.navegar();
		
		//Inserir nome e sobre nome
		conectarCadastro.cadastroPage().inserirNome().sendKeys(nome);
		conectarCadastro.cadastroPage().inserirSobrenome().sendKeys(sobrenome);
		
		//Selecionar o sexo
		if(sexo.equals("Masculino")) conectarCadastro.cadastroPage().selecionarRadioSexoMasculino().click();
		if(sexo.equals("Feminino")) conectarCadastro.cadastroPage().selecionarRadioSexoFeminino().click();
		
		//Selecionar a escolaridade
		conectarCadastro.cadastroPage().selecionarComboEscolaridade(escolaridade);
		
		//Selecionar as opções de comida
		if(comidas.contains("Carne")) conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaCarne().click();
		if(comidas.contains("Pizza")) conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaPizza().click();
		if(comidas.contains("Vegetariano")) conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaVegetariano().click();
		
		//Selecionar as opções de esportes
		if(esportes.contains("Karate")) conectarCadastro.cadastroPage().clickSelecaoEsporte("Karate");
		if(esportes.contains("O que eh esporte?")) conectarCadastro.cadastroPage().clickSelecaoEsporte("O que eh esporte?");
		
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		
		System.out.println(msg);
		Assert.assertEquals(msg, conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		conectarCadastro.cadastroPage().acessarJanelaAlert().accept();
		
	}

}
