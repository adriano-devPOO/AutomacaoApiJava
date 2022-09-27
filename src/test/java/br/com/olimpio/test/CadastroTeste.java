package br.com.olimpio.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.olimpio.usuario.Usuario;
import br.com.olimpio.util.CadastroConectar;


public class CadastroTeste {

	private static WebDriver driver;
	private static Usuario usuario;
	private static CadastroConectar conectarCadastro;
	//public static WebDriverWait wait;
	

	@BeforeClass
	public static void inicio() {
		driver = new ChromeDriver();
		usuario = new Usuario();
		conectarCadastro = new CadastroConectar(driver);
		//wait = new WebDriverWait(driver, 20);
	}

	@AfterClass
	public static void limparConexao() {
		driver.quit();
	}
	
	public void validartituloTextoHome() {
		//Acessar o navegador
		conectarCadastro.navegar();
		//Validar título da página
		Assert.assertEquals(conectarCadastro.cadastroPage().validarTextoHome().getText(), "Campo de Treinamento");
	}

	@Test
	public void telaCadastroUsuario() {
		validartituloTextoHome();
		
		//Validar nome do usuário inserido
		//elementos().elementId(inputNome).sendKeys(usuario().getNome());
		conectarCadastro.cadastroPage().inserirNome().sendKeys(usuario.getNome());
		Assert.assertEquals(usuario.getNome(), conectarCadastro.cadastroPage().inserirNome().getAttribute("value"));
		//Validar sobrenome do usuário inserido
		conectarCadastro.cadastroPage().inserirSobrenome().sendKeys(usuario.getSobrenome());
		Assert.assertEquals(usuario.getSobrenome(), conectarCadastro.cadastroPage().inserirSobrenome().getAttribute("value"));
		//Validar sugestão do usuário inserido
		conectarCadastro.cadastroPage().inserirSugestoes().sendKeys(usuario.getTextoSugetoes());
		Assert.assertEquals(usuario.getTextoSugetoes(), conectarCadastro.cadastroPage().inserirSugestoes().getAttribute("value"));
		//Validar click na opção sexo masculino
		conectarCadastro.cadastroPage().selecionarRadioSexoMasculino().click();
		Assert.assertTrue(conectarCadastro.cadastroPage().selecionarRadioSexoMasculino().isSelected());
		//Validar click na opção de comida vegetariana
		conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaVegetariano().click();
		Assert.assertTrue(conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaVegetariano().isSelected());
		//Validar a seleção da escolaridade, doutorado
		conectarCadastro.cadastroPage().selecionarComboEscolaridade("Superior");
		//Validar a seleção de multiplos esporte, sendo: corrida, karate e o que é esportes?
		Assert.assertEquals(3, conectarCadastro.cadastroPage().selecionarMultiplosEspotes().size());
		//Validar txto do botão cuique me
		Assert.assertEquals("Clique Me!", conectarCadastro.cadastroPage().clickBotaoCliqueMe().getAttribute("value"));
		//Click no botão clique me
		conectarCadastro.cadastroPage().clickBotaoCliqueMe().click();
		//Validar texto do status
		Assert.assertEquals("Status: Nao cadastrado", conectarCadastro.cadastroPage().valitarTextoStatus().getText());
		//Click no link voltar
		conectarCadastro.cadastroPage().clickLinkVoltar().click();
		//Validar texto do status
		Assert.assertEquals("Voltou!", conectarCadastro.cadastroPage().valitarTextoStatus().getText());
	}

	@Test
	public void testeAlertClickBotaoAlert() {
		validartituloTextoHome();

		conectarCadastro.cadastroPage().clickBotaoAlertSimples().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Alert Simples", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
	}
	
	@Test
	public void testeAlertClickBotaoConfirm() {
		validartituloTextoHome();
		
		//Cenário que o usuário clica no botão confirmar
		conectarCadastro.cadastroPage().clickBotaoAlertComfirm().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Confirm Simples", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
				//Validar o texto do alert
				Assert.assertEquals("Confirmado", conectarCadastro.cadastroPage().acessarAlert().getText());
				//Clicar no botão OK
				conectarCadastro.cadastroPage().acessarAlert().accept();
				
		//Cenário que o usuário clica no botão cancelar
		conectarCadastro.cadastroPage().clickBotaoAlertComfirm().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Confirm Simples", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão Cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals("Negado", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
	}
	
	@Test
	public void testeAlertClickBotaoPrompt() {
		validartituloTextoHome();
		
		conectarCadastro.cadastroPage().clickBotaoAlertPrompt().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Digite um numero", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Inserir um valor no input
			conectarCadastro.cadastroPage().acessarAlert().sendKeys("147");
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
			//Validar o texto do alert
			Assert.assertEquals("Era 147?", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
			//Validar o texto do alert
			Assert.assertEquals(":D", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
		
		conectarCadastro.cadastroPage().clickBotaoAlertPrompt().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Digite um numero", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Inserir um valor no input
			conectarCadastro.cadastroPage().acessarAlert().sendKeys("147");
			//Clicar no botão cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals("Era null?", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
			//Validar o texto do alert
			Assert.assertEquals(":D", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
		
		conectarCadastro.cadastroPage().clickBotaoAlertPrompt().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Digite um numero", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Inserir um valor no input
			conectarCadastro.cadastroPage().acessarAlert().sendKeys("147");
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals("Era null?", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals(":(", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
		
		conectarCadastro.cadastroPage().clickBotaoAlertPrompt().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Digite um numero", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals("Era null?", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals(":(", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no botão OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
	}

	@Test
	public void testeFrameClickBotaoDentroDoFrame() {
		validartituloTextoHome();
		
		//clicar no botão do frame, nome: Dentro do frame
		conectarCadastro.cadastroPage().clickBotaoOkFrame().click();
			//Acessar a área do frame
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar texto
			Assert.assertEquals("Frame OK!", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Cicar no botão ok
			conectarCadastro.cadastroPage().acessarAlert().accept();
	}

	@Test
	public void testeJanelaComTitulo() {
		validartituloTextoHome();
		
		// abrir popup, escrever o texto e fechar
		conectarCadastro.cadastroPage().clickBotaoJanelaComTitulo().click();
			//Inserir título da popup
			conectarCadastro.cadastroPage().inserirTituloPopup("Popup");
			//Inserir um texto
			conectarCadastro.cadastroPage().inputTextArea().sendKeys("Consegui!");
			//Fechar janela
			conectarCadastro.cadastroPage().fecharJanela();
			// voltar para a tela principal
			conectarCadastro.cadastroPage().inserirTituloPopup("");
			//Inserir um nome
			conectarCadastro.cadastroPage().inserirNome().sendKeys(usuario.getNome());
	}

	@Test
	public void testeJanelaSemTitulo() {
		validartituloTextoHome();

		conectarCadastro.cadastroPage().clickBotaoJanelaSemTitulo().click();
			//Abrir popup
			conectarCadastro.cadastroPage().abrirPopUpSemTituloDeReferencia();
			//Inserir um texto
			conectarCadastro.cadastroPage().inputTextArea().sendKeys("Consegui com ajuda.");
			// voltar para a tela principal
			conectarCadastro.cadastroPage().voltarTelaPrincipalPopUpSemTituloDeReferencia();
			//Inserir um nome
			conectarCadastro.cadastroPage().inserirNome().sendKeys(usuario.getNome());
			//Inserir um sobrenome
			conectarCadastro.cadastroPage().inserirSobrenome().sendKeys(usuario.getSobrenome());
	}
	
	@Test
	public void validarCamposObrigatorios() {
		validartituloTextoHome();
		//clicar no botão cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar campo sexo sem selecionar valor
		Assert.assertEquals("Nome eh obrigatorio", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no botão OK da janela
		conectarCadastro.cadastroPage().acessarAlert().accept();
		//Inserir o nome
		conectarCadastro.cadastroPage().inserirNome().sendKeys(usuario.getNome());
		//clicar no botão cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar campo sobrenome vazio
		Assert.assertEquals("Sobrenome eh obrigatorio", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no botão OK da janela
		conectarCadastro.cadastroPage().acessarAlert().accept();
		//Inserir o sobrenome
		conectarCadastro.cadastroPage().inserirSobrenome().sendKeys(usuario.getSobrenome());
		//clicar no botão cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar campo sexo sem selecionar valor
		Assert.assertEquals("Sexo eh obrigatorio", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no botão OK da janela
		conectarCadastro.cadastroPage().acessarAlert().accept();
		//selecionar o sexo masculino
		conectarCadastro.cadastroPage().selecionarRadioSexoMasculino().click();
		//Selecionar as comidas
		conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaVegetariano().click();
		conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaCarne().click();
		//clicar no botão cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar texto que aparece na janela
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no botão OK da janela
		conectarCadastro.cadastroPage().acessarJanelaAlert().dismiss();
		// clicar na opção carne para desmarcar
		conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaCarne().click();
		//Selecionar os esportes
		conectarCadastro.cadastroPage().clickSelecaoEsporte("Karate");
		conectarCadastro.cadastroPage().clickSelecaoEsporte("O que eh esporte?");
		//clicar no botão cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar texto que aparece na janela
		Assert.assertEquals("Voce faz esporte ou nao?", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no botão OK da janela
		conectarCadastro.cadastroPage().acessarJanelaAlert().dismiss();
		//Remover a seleção da opção: o que eh sporte?
		conectarCadastro.cadastroPage().deselectSelecaoEsporte("O que eh esporte?");
		//Selecionar o esporte
		conectarCadastro.cadastroPage().clickSelecaoEsporte("Corrida");
		//clicar no botão cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		
		//Falta validar o cadastro com sucesso
	}

}
