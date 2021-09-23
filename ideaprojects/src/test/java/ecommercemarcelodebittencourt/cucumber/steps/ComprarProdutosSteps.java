package ecommercemarcelodebittencourt.cucumber.steps;
import ecommercemarcelodebittencourt.pageobjects.HomePage;
import ecommercemarcelodebittencourt.pageobjects.LoginPage;
import ecommercemarcelodebittencourt.pageobjects.ModalProdutoPage;
import ecommercemarcelodebittencourt.pageobjects.ProdutoPage;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.messages.internal.com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
    ModalProdutoPage modalProdutoPage;


    //Valores válidos
    String email_Valido = "acedospunhosbemquentinho@gmail.com";
    String senha_Valida = "BarbaBranca<3";

    //Variáveis
    String nomeProduto_ProdutoPage;
    String precoProduto_ProdutoPage;
    String nomeProduto_HomePage;
    String precoProduto_HomePage;

    @Before
    public void inicializar() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pichau\\drivers\\chromedriver(94).exe");
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

        nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePage);

        ProdutoPage produtoPage = homePage.clicarProduto(indice);

        nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
        precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();

    }

    @Quando("nome do produto na tela principal e na tela produto eh {string}")
    public void nome_do_produto_na_tela_principal_eh(String nomeProduto) {
        assertThat(nomeProduto_HomePage.toUpperCase(),is(nomeProduto.toUpperCase()));
        assertThat(nomeProduto_ProdutoPage.toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
    }

    @Quando("preco do produto na tela principal e na tela produto eh {string}")
    public void preco_do_produto_na_tela_principal_eh(String precoProduto) {
        assertThat(precoProduto_HomePage,is(precoProduto));
        assertThat(precoProduto_ProdutoPage, is(precoProduto_ProdutoPage));
    }

    @Quando("adiciono produto no carrinho com tamanho {string} cor {string} e quantidade {int}")
    public void adiciono_produto_no_carrinho_com_tamanho_cor_e_quantidade(String tamanhoProduto, String corProduto, Integer quantidadeProduto) {
        homePage = new HomePage(driver);
        produtoPage = new ProdutoPage(driver);
        modalProdutoPage  = new ModalProdutoPage(driver);

        //Selecionar Tamanho
        List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();

        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        produtoPage.selecionarOpcaoDropdown(tamanhoProduto);
        listaOpcoes = produtoPage.obterOpcoesSelecionadas();

        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        //Selecionar Cor
        if (!corProduto.equals("N/A"))
            produtoPage.alterarCor();

        //Selecionar quantidade
        produtoPage.alterarQuantidade(Integer.toString(quantidadeProduto));

        //Adicionar ao Carrinho
        modalProdutoPage = produtoPage.clicarAddToCart();

        //Validações
        assertThat(modalProdutoPage.obterMensagemProdutoAdcionado().
                endsWith("Product successfully added to your shopping cart"), is(true));
        assertThat(modalProdutoPage.obterCorProduto(), is(corProduto));
        assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));

        int quantidadeProdutoInt = Integer.parseInt(modalProdutoPage.obterQuantidadeProduto());
        assertThat(quantidadeProdutoInt, is(quantidadeProduto));


    }

    //Resultados esperados
    @Entao("o produto aparece na confirmacao com nome {string} preco {string} tamanho {string} cor {string} e quantidade {int}")
    public void o_produto_aparece_na_confirmacao_com_nome_preco_tamanho_cor_e_quantidade(String nomeProduto, String precoProduto, String tamanhoProduto, String corProduto ,Integer quantidadeProduto) {

        assertThat(modalProdutoPage.obterDescricaoProduto().toUpperCase(), is(nomeProduto.toUpperCase()));

        Double precoProdutoDoubleEncontrado = Double.parseDouble(modalProdutoPage.obterPrecoProduto().replace("$",""));

        assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));
        if(!precoProduto.equals("N/A"))
            assertThat(modalProdutoPage.obterCorProduto(), is(corProduto));
        int quantidadeProdutoEsperada = Integer.parseInt(modalProdutoPage.obterQuantidadeProduto());
        assertThat(quantidadeProdutoEsperada, is(quantidadeProduto));

        String subtotalString = modalProdutoPage.obterSubtotal();
        subtotalString = subtotalString.replace("$","");
        Double subtotalEsperado = Double.parseDouble(subtotalString);

        Double subtotalCalculadoEsperado = quantidadeProduto * precoProdutoDoubleEncontrado;
        assertThat(subtotalCalculadoEsperado, is(subtotalEsperado));

    }

    @After(order = 1)
    public void capturarTela(Scenario scenario) {
        TakesScreenshot camera = (TakesScreenshot) driver;
        File capturaDeTela = camera.getScreenshotAs(OutputType.FILE);

        String scenarioId = scenario.getId().substring(scenario.getId().lastIndexOf(".feature:") + 9);
        String nomeArquivo = "src/test/java/ecommercemarcelodebittencourt/screenshots/" + scenarioId + "_" + scenario.getStatus() + ".png";
        System.out.println(nomeArquivo);

        try {
            Files.move(capturaDeTela, new File(nomeArquivo));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    @After(order = 0)
    public void Finalizar() {
        driver.close();
        driver.quit();
    }

}
