package ecommercemarcelodebittencourt.tests;

import ecommercemarcelodebittencourt.pageobjects.HomePage;
import ecommercemarcelodebittencourt.pageobjects.LoginPage;
import ecommercemarcelodebittencourt.pageobjects.ProdutoPage;
import ecommercemarcelodebittencourt.setup.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

public class HomePageTests {

    private WebDriver driver;
    private ProdutoPage PageProduto;

    //Executado anterior a todos os processos de testes
    @Before
    public void CriarDriver() {
        Driver wdriver = new Driver();
        driver = wdriver.WebDriver();
    }

    //Executado ao final de todos os processos de testes
    @After
    public void EncerrarDriver() {
        driver.close();
        driver.quit();
    }

    //Campo de Testes

    @Test
    public void ContarProdutos() {
        int produtos = 8;
        HomePage homepage = new HomePage(driver);
        homepage.contarProdutos(produtos);
    }

    @Test
    public void CarrinhoVazio() {
        int carrinhoValor = 0;
        HomePage homepage = new HomePage(driver);
        homepage.carrinhoVazio(carrinhoValor);
    }

    @Test
    public void DetalhesProduto() {
        int indice = 0;
        HomePage homepage = new HomePage(driver);
        String nomeProduto_HomePage = homepage.obterNomeProduto(indice);
        String precoProduto_HomePage = homepage.obterPrecoProduto(indice);

        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePage);

        ProdutoPage produtoPage = homepage.clicarProduto(indice);
        String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();

        System.out.println(nomeProduto_ProdutoPage);
        System.out.println(precoProduto_ProdutoPage);

        //Validando nomes e preços
        Assert.assertEquals(nomeProduto_HomePage.toUpperCase(),nomeProduto_ProdutoPage.toUpperCase());
        Assert.assertEquals(precoProduto_HomePage.toUpperCase(), precoProduto_ProdutoPage.toUpperCase());
    }

    @Test
    public void LoginValido() {
        HomePage homepage = new HomePage(driver);

        //Clicar no botão Sign In na Homepage
        LoginPage loginPage = homepage.clicarBotaoSignIn();

        //Preencher campos
        loginPage.preencherEmail("acedospunhosbemquentinho@gmail.com");
        loginPage.preencherSenha("BarbaBranca<3");

        //Clicar no botão Sign In para logar
        loginPage.clicarBotaoSignIn();

        //Validar se o usuário está logado corretamente
        Assert.assertTrue(homepage.estaLogado("Portgas D. Ace"));
    }
}
