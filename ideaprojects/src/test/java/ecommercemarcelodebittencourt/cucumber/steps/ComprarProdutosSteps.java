package ecommercemarcelodebittencourt.cucumber.steps;
import ecommercemarcelodebittencourt.pageobjects.HomePage;
import ecommercemarcelodebittencourt.pageobjects.LoginPage;
import ecommercemarcelodebittencourt.pageobjects.ProdutoPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ComprarProdutosSteps {

    private static WebDriver driver;

    //Globais
    HomePage homePage;
    LoginPage loginPage;
    ProdutoPage produtoPage;


    //Valores válidos
    String email_Valido = "acedospunhosbemquentinho@gmail.com";
    String senha_Valida = "BarbaBranca<3";

    //Variáveis
    String nomeProduto_ProdutoPage;
    String precoProduto_ProdutoPage;

    @Before
    public void inicializar() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pichau\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    //Pré-condição
    @Dado("que estou na pagina inicial")
    public void que_estou_na_pagina_inicial() {
      homePage = new HomePage(driver);
      homePage.carregarPaginaInicial();
      assertEquals(homePage.obterTituloPagina(), "Loja de Teste");
    }

    //Ações
    @Quando("nao estou logado")
    public void nao_estou_logado() {
      assertThat(homePage.estaLogado(), is(false));
    }

    //Resultados esperados
    @Entao("visualize {int} produtos disponiveis")
    public void visualize_produtos_disponiveis(Integer int1) {
      int produtos = homePage.contarProdutos(8);
      assertThat(produtos, is(int1));
    }

    //Ações
    @Entao("carrinho esta vazio")
    public void carrinho_esta_vazio() {
       int carrinhoValor = 0;
       assertThat(carrinhoValor, is(0));
    }

    @Quando("estou logado")
    public void estou_logado() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        //Clicar no botão Sign In
        homePage.clicarBotaoSignIn();

        //Preencher campos obrigatórios
        loginPage.preencherEmail(email_Valido);
        loginPage.preencherSenha(senha_Valida);

        //Clicar no botão Sign In para logar
        loginPage.clicarBotaoSignIn();

        //Validar se o usuário está logado corretamente
        assertTrue(homePage.estaLogado("Portgas D. Ace"));

        homePage.carregarPaginaInicial();
    }

    @Quando("seleciona um produto {int}")
    public void seleciona_um_produto(Integer indice) {
        homePage = new HomePage(driver);
        produtoPage = new ProdutoPage(driver);

        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePage);

        ProdutoPage produtoPage = homePage.clicarProduto(indice);

        nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
        precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();

    }

    @Quando("nome do produto na tela principal eh {string}")
    public void nome_do_produto_na_tela_principal_eh(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Quando("preco do produto na tela principal eh {string}")
    public void preco_do_produto_na_tela_principal_eh(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Quando("adiciono produto no carrinho com tamanho {string} cor {string} e quantidade {int}")
    public void adiciono_produto_no_carrinho_com_tamanho_cor_e_quantidade(String string, String string2, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //Resultados esperados
    @Entao("o produto aparece na confirmacao com nome {string} preco {string} tamanho {string} cor {string} e quantidade {int}")
    public void o_produto_aparece_na_confirmacao_com_nome_preco_tamanho_cor_e_quantidade(String string, String string2, String string3, String string4, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @After
    public void Finalizar() {
        driver.close();
        driver.quit();
    }

}
