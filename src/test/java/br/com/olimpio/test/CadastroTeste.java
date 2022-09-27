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
		//Validar t�tulo da p�gina
		Assert.assertEquals(conectarCadastro.cadastroPage().validarTextoHome().getText(), "Campo de Treinamento");
	}

	@Test
	public void telaCadastroUsuario() {
		validartituloTextoHome();
		
		//Validar nome do usu�rio inserido
		//elementos().elementId(inputNome).sendKeys(usuario().getNome());
		conectarCadastro.cadastroPage().inserirNome().sendKeys(usuario.getNome());
		Assert.assertEquals(usuario.getNome(), conectarCadastro.cadastroPage().inserirNome().getAttribute("value"));
		//Validar sobrenome do usu�rio inserido
		conectarCadastro.cadastroPage().inserirSobrenome().sendKeys(usuario.getSobrenome());
		Assert.assertEquals(usuario.getSobrenome(), conectarCadastro.cadastroPage().inserirSobrenome().getAttribute("value"));
		//Validar sugest�o do usu�rio inserido
		conectarCadastro.cadastroPage().inserirSugestoes().sendKeys(usuario.getTextoSugetoes());
		Assert.assertEquals(usuario.getTextoSugetoes(), conectarCadastro.cadastroPage().inserirSugestoes().getAttribute("value"));
		//Validar click na op��o sexo masculino
		conectarCadastro.cadastroPage().selecionarRadioSexoMasculino().click();
		Assert.assertTrue(conectarCadastro.cadastroPage().selecionarRadioSexoMasculino().isSelected());
		//Validar click na op��o de comida vegetariana
		conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaVegetariano().click();
		Assert.assertTrue(conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaVegetariano().isSelected());
		//Validar a sele��o da escolaridade, doutorado
		conectarCadastro.cadastroPage().selecionarComboEscolaridade("Superior");
		//Validar a sele��o de multiplos esporte, sendo: corrida, karate e o que � esportes?
		Assert.assertEquals(3, conectarCadastro.cadastroPage().selecionarMultiplosEspotes().size());
		//Validar txto do bot�o cuique me
		Assert.assertEquals("Clique Me!", conectarCadastro.cadastroPage().clickBotaoCliqueMe().getAttribute("value"));
		//Click no bot�o clique me
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
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
	}
	
	@Test
	public void testeAlertClickBotaoConfirm() {
		validartituloTextoHome();
		
		//Cen�rio que o usu�rio clica no bot�o confirmar
		conectarCadastro.cadastroPage().clickBotaoAlertComfirm().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Confirm Simples", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
				//Validar o texto do alert
				Assert.assertEquals("Confirmado", conectarCadastro.cadastroPage().acessarAlert().getText());
				//Clicar no bot�o OK
				conectarCadastro.cadastroPage().acessarAlert().accept();
				
		//Cen�rio que o usu�rio clica no bot�o cancelar
		conectarCadastro.cadastroPage().clickBotaoAlertComfirm().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Confirm Simples", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o Cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals("Negado", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o OK
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
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
			//Validar o texto do alert
			Assert.assertEquals("Era 147?", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
			//Validar o texto do alert
			Assert.assertEquals(":D", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
		
		conectarCadastro.cadastroPage().clickBotaoAlertPrompt().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Digite um numero", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Inserir um valor no input
			conectarCadastro.cadastroPage().acessarAlert().sendKeys("147");
			//Clicar no bot�o cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals("Era null?", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
			//Validar o texto do alert
			Assert.assertEquals(":D", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
		
		conectarCadastro.cadastroPage().clickBotaoAlertPrompt().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Digite um numero", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Inserir um valor no input
			conectarCadastro.cadastroPage().acessarAlert().sendKeys("147");
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals("Era null?", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals(":(", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
		
		conectarCadastro.cadastroPage().clickBotaoAlertPrompt().click();
			//Acessar o alert
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar o texto do alert
			Assert.assertEquals("Digite um numero", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals("Era null?", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o cancelar
			conectarCadastro.cadastroPage().acessarAlert().dismiss();
			//Validar o texto do alert
			Assert.assertEquals(":(", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Clicar no bot�o OK
			conectarCadastro.cadastroPage().acessarAlert().accept();
	}

	@Test
	public void testeFrameClickBotaoDentroDoFrame() {
		validartituloTextoHome();
		
		//clicar no bot�o do frame, nome: Dentro do frame
		conectarCadastro.cadastroPage().clickBotaoOkFrame().click();
			//Acessar a �rea do frame
			conectarCadastro.cadastroPage().acessarAlert();
			//Validar texto
			Assert.assertEquals("Frame OK!", conectarCadastro.cadastroPage().acessarAlert().getText());
			//Cicar no bot�o ok
			conectarCadastro.cadastroPage().acessarAlert().accept();
	}

	@Test
	public void testeJanelaComTitulo() {
		validartituloTextoHome();
		
		// abrir popup, escrever o texto e fechar
		conectarCadastro.cadastroPage().clickBotaoJanelaComTitulo().click();
			//Inserir t�tulo da popup
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
		//clicar no bot�o cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar campo sexo sem selecionar valor
		Assert.assertEquals("Nome eh obrigatorio", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no bot�o OK da janela
		conectarCadastro.cadastroPage().acessarAlert().accept();
		//Inserir o nome
		conectarCadastro.cadastroPage().inserirNome().sendKeys(usuario.getNome());
		//clicar no bot�o cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar campo sobrenome vazio
		Assert.assertEquals("Sobrenome eh obrigatorio", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no bot�o OK da janela
		conectarCadastro.cadastroPage().acessarAlert().accept();
		//Inserir o sobrenome
		conectarCadastro.cadastroPage().inserirSobrenome().sendKeys(usuario.getSobrenome());
		//clicar no bot�o cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar campo sexo sem selecionar valor
		Assert.assertEquals("Sexo eh obrigatorio", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no bot�o OK da janela
		conectarCadastro.cadastroPage().acessarAlert().accept();
		//selecionar o sexo masculino
		conectarCadastro.cadastroPage().selecionarRadioSexoMasculino().click();
		//Selecionar as comidas
		conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaVegetariano().click();
		conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaCarne().click();
		//clicar no bot�o cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar texto que aparece na janela
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no bot�o OK da janela
		conectarCadastro.cadastroPage().acessarJanelaAlert().dismiss();
		// clicar na op��o carne para desmarcar
		conectarCadastro.cadastroPage().selecionarCheckBoxComidaFavoritaCarne().click();
		//Selecionar os esportes
		conectarCadastro.cadastroPage().clickSelecaoEsporte("Karate");
		conectarCadastro.cadastroPage().clickSelecaoEsporte("O que eh esporte?");
		//clicar no bot�o cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		//Validar texto que aparece na janela
		Assert.assertEquals("Voce faz esporte ou nao?", conectarCadastro.cadastroPage().acessarJanelaAlert().getText());
		//clicar no bot�o OK da janela
		conectarCadastro.cadastroPage().acessarJanelaAlert().dismiss();
		//Remover a sele��o da op��o: o que eh sporte?
		conectarCadastro.cadastroPage().deselectSelecaoEsporte("O que eh esporte?");
		//Selecionar o esporte
		conectarCadastro.cadastroPage().clickSelecaoEsporte("Corrida");
		//clicar no bot�o cadastrar
		conectarCadastro.cadastroPage().clickBotaoCadastrar().click();
		
		//Falta validar o cadastro com sucesso
	}

}
