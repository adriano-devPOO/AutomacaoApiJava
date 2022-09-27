package br.com.olimpio.element;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.olimpio.usuario.Usuario;

public class CadastroPage {

	private final WebDriver driver;
	private Select combo;

	private By textTitulo = By.xpath("//*[@id='elementosForm']/h3");
	private By textStatus = By.id("resultado");

	private By inputNome = By.id("elementosForm:nome");
	private By inputSobrenome = By.id("elementosForm:sobrenome");
	private By inputSugestao = By.id("elementosForm:sugestoes");
	private By inputTextarea = By.tagName("textarea");

	private By radioSexoMasculino = By.id("elementosForm:sexo:0");
	private By radioSexoFeminino = By.id("elementosForm:sexo:1");

	private By checkboxComidaCarne = By.id("elementosForm:comidaFavorita:0");
	private By checkboxComidaPizza = By.id("elementosForm:comidaFavorita:2");
	private By checkboxComidaVegetariano = By.id("elementosForm:comidaFavorita:3");

	private By selectEscolaridade = By.id("elementosForm:escolaridade");
	private By selectEsportes = By.id("elementosForm:esportes");

	private By clickBotaoCliqueMe = By.id("buttonSimple");
	private By clickBotaoCadastrar = By.id("elementosForm:cadastrar");

	private By clickBotaoAlert = By.id("alert");
	private By clickBotaoAlertComfirm = By.id("confirm");
	private By clickBotaoAlertPrompt = By.id("prompt");

	private By clickBotaoFrame = By.id("frameButton");

	private By clickLinkBotaoVoltar = By.linkText("Voltar");
	private By clickLinkBotaoPopupEasy = By.id("buttonPopUpEasy");
	private By clickLinkBotaoPopupHard = By.id("buttonPopUpHard");

	public CadastroPage(WebDriver driver) {
		this.driver = driver;
	}

	protected CadastroElementos elementos() {
		return new CadastroElementos(driver);
	}

	protected Usuario usuario() {
		return new Usuario();
	}

	public WebElement validarTextoHome() {
		return elementos().elementXpath(textTitulo);
	}

	public WebElement inserirNome() {
		return elementos().elementId(inputNome);
	}

	public WebElement inserirSobrenome() {
		return elementos().elementId(inputSobrenome);
	}

	public WebElement inserirSugestoes() {
		return elementos().elementId(inputSugestao);
	}

	public WebElement selecionarRadioSexoMasculino() {
		return elementos().elementId(radioSexoMasculino);
	}
	
	public WebElement selecionarRadioSexoFeminino() {
		return elementos().elementId(radioSexoFeminino);
	}

	public WebElement selecionarCheckBoxComidaFavoritaVegetariano() {
		return elementos().elementId(checkboxComidaVegetariano);
	}

	public WebElement selecionarCheckBoxComidaFavoritaCarne() {
		return elementos().elementId(checkboxComidaCarne);
	}

	public WebElement selecionarCheckBoxComidaFavoritaPizza() {
		return elementos().elementId(checkboxComidaPizza);
	}

	public Select selecionarComboEscolaridade(String texto) {
		combo = new Select(elementos().elementId(selectEscolaridade));
		// Selecionar por texto visível, texto = Doutorado

		if (texto.equals("")) {
			return combo;
		}else {
			
			while (texto != null) {
				combo.selectByVisibleText(texto);
				combo.getFirstSelectedOption();

				if (combo.getFirstSelectedOption().getText().equals(texto)) {
					return combo;
				} else {
					System.out.println("Erro na verificação da escolaridade: " + texto);
					return combo = null;
				}

			}
			
		}

		return combo;
	}

	public Select validarEscolaridadeDoutoradoPeloIndex(int valor) {
		combo = new Select(elementos().elementId(selectEscolaridade));
		// Selecionar com valor de index, valor = 2
		combo.selectByIndex(valor);
		return combo;
	}

	public Select validarEscolaridadeDoutoradoPeloValue(String valor) {
		combo = new Select(elementos().elementId(selectEscolaridade));
		// Selecionar com value, exemplo de valor = especializacao
		combo.selectByValue(valor);
		return combo;
	}

	public boolean validarOpcoesEscolaridade() {
		combo = new Select(elementos().elementId(selectEscolaridade));
		// Outras verificações
		List<WebElement> opcoes = combo.getOptions();

		// Verifica a quantidade de opeções na lista
		Assert.assertEquals(8, opcoes.size());

		// Verifica se a opção desejada está na lista
		boolean encontrou = false;

		for (WebElement opcao : opcoes) {
			if (opcao.getText().equals("1o grau completo")) {
				encontrou = true;
				break;
			}
		}
		return encontrou;
	}

	public Select clickSelecaoEsporte(String texto) {
		combo = new Select(elementos().elementId(selectEsportes));

		while (texto != null) {
			if(texto.equals("")) {
				return combo = null;
			}
			combo.selectByVisibleText(texto);
			return combo;
		}
		return combo;
	}

	public Select deselectSelecaoEsporte(String texto) {
		combo = new Select(elementos().elementId(selectEsportes));

		while (texto != null) {
			combo.deselectByVisibleText(texto);
			return combo;
		}
		return combo;
	}

	public List<Select> selecionarMultiplosEspotes() {

		List<Select> esportes = new ArrayList<>();

		esportes.add(clickSelecaoEsporte("Corrida"));
		esportes.add(clickSelecaoEsporte("Karate"));
		esportes.add(clickSelecaoEsporte("Natacao"));

		return esportes;

	}
	
	public boolean validarTextoBotaoCliqueMe() {
		return elementos().elementId(clickBotaoCliqueMe).getAttribute("value").equals("Clique Me!");
	}

	public WebElement clickBotaoCliqueMe() {
		WebElement clickBotao = null;

		if (validarTextoBotaoCliqueMe()) {
			clickBotao = elementos().elementId(clickBotaoCliqueMe);
			return clickBotao;
		}
		return clickBotao;

	}

	public WebElement valitarTextoStatus() {
		return elementos().elementId(textStatus);
	}

	public WebElement clickLinkVoltar() {
		return elementos().linkVoltar(clickLinkBotaoVoltar);
	}

	public Alert acessarAlert() {
		return elementos().getDriver().switchTo().alert();
	}

	public WebElement clickBotaoAlertSimples() {
		return elementos().elementId(clickBotaoAlert);
	}

	public WebElement clickBotaoAlertComfirm() {
		return elementos().elementId(clickBotaoAlertComfirm);
	}

	public WebElement clickBotaoAlertPrompt() {
		return elementos().elementId(clickBotaoAlertPrompt);
	}

	public WebElement clickBotaoOkFrame() {
		elementos().getDriver().switchTo().frame("frame1");
		return elementos().elementId(clickBotaoFrame);
	}

	public WebElement clickBotaoCadastrar() {
		return elementos().elementId(clickBotaoCadastrar);
	}

	public WebElement clickBotaoJanelaComTitulo() {
		// abrir popup, escrever o texto e fechar
		return elementos().elementId(clickLinkBotaoPopupEasy);
	}

	public void inserirTituloPopup(String texto) {
		// Método para acessar a janela com título de referencia do popup:
		// javascript:createPopup('Popup')
		elementos().getDriver().switchTo().window(texto);
	}

	public WebElement inputTextArea() {
		return elementos().elementTagName(inputTextarea);
	}

	public void fecharJanela() {
		elementos().getDriver().close();
	}

	public WebElement clickBotaoJanelaSemTitulo() {
		// abrir popup, escrever o texto e fechar
		return elementos().elementId(clickLinkBotaoPopupHard);
	}

	public void abrirPopUpSemTituloDeReferencia() {
		// Abrir popup sem titulo de referencia: javascript:createPopup('')
		// Imprimir o windowsHadle para conseguir abrir popup
//		System.out.println(elementos().getDriver().getWindowHandle()); // página principal
//		System.out.println(elementos().getDriver().getWindowHandles()); // página principal e popup

		// encontrar o id do popup desejado, depois de clicar no botão para abrir a
		// janela (popup)
		elementos().getDriver().switchTo().window((String) elementos().getDriver().getWindowHandles().toArray()[1]);
	}

	public void voltarTelaPrincipalPopUpSemTituloDeReferencia() {
		// voltar para a tela principal
		elementos().getDriver().switchTo().window((String) elementos().getDriver().getWindowHandles().toArray()[0]);
	}

	public Alert acessarJanelaAlert() {
		return elementos().getDriver().switchTo().alert();
	}

//	public void validarCadastroComSucesso() {
//
//		Assert.assertTrue(elementos().elementId("resultado").getText().startsWith("Cadastrado!"));
//		Assert.assertTrue(elementos().elementId("descNome").getText().endsWith(usuario().getNome()));
//		Assert.assertEquals("Sobrenome: " + usuario().getSobrenome(),
//				elementos().elementXpath("//*[@id=\"descSobrenome\"]").getText());
//		Assert.assertEquals("Sexo: Masculino", elementos().elementXpath("//*[@id=\"descSexo\"]").getText());
//		Assert.assertEquals("Comida: Carne Pizza", elementos().elementXpath("//*[@id=\"descComida\"]").getText());
//		Assert.assertEquals("Escolaridade: superior",
//				elementos().elementXpath("//*[@id=\"descEscolaridade\"]").getText());
//		Assert.assertEquals("Esportes: Natacao Futebol Corrida",
//				elementos().elementXpath("//*[@id=\"descEsportes\"]").getText());
//		Assert.assertEquals("Sugestoes: " + usuario().getTextoSugetoes(),
//				elementos().elementXpath("//*[@id=\"descSugestoes\"]").getText());
//
//	}

}
