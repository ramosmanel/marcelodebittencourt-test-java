package ecommercemarcelodebittencourt.tests;

import ecommercemarcelodebittencourt.pageobjects.*;
import ecommercemarcelodebittencourt.setup.Driver;
import ecommercemarcelodebittencourt.util.Funcoes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePageTests {

    private WebDriver driver;
    private ProdutoPage produtoPage;

    //Executado anterior a todos os processos de testes
    @Before
    public void CriarDriver() {
        Driver wdriver = new Driver();
        this.driver = wdriver.WebDriver();
    }

    //Executado ao final de todos os processos de testes
    @After
    public void EncerrarDriver() {
        driver.close();
        driver.quit();
    }

    //Globais
    HomePage homepage;
    LoginPage loginPage;
    ModalProdutoPage modalProdutoPage;
    CarrinhoPage carrinhoPage;
    CheckoutPage checkoutPage;
    PedidoPage pedidoPage;

    //Campo de Testes

    @Test
    public void ContarProdutos() {
        int produtos = 8;
        homepage = new HomePage(driver);
        homepage.contarProdutos(produtos);
    }

    @Test
    public void CarrinhoVazio() {
        int carrinhoValor = 0;
        HomePage homepage = new HomePage(driver);
        homepage.carrinhoVazio(carrinhoValor);
    }

    String nomeProduto_ProdutoPage;
    @Test
    public void DetalhesProduto() {
        int indice = 0;
        homepage = new HomePage(driver);
        String nomeProduto_HomePage = homepage.obterNomeProduto(indice);
        String precoProduto_HomePage = homepage.obterPrecoProduto(indice);

        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePage);

        ProdutoPage produtoPage = homepage.clicarProduto(indice);
        nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();

        System.out.println(nomeProduto_ProdutoPage);
        System.out.println(precoProduto_ProdutoPage);

        //Validando nomes e preços
        assertEquals(nomeProduto_HomePage.toUpperCase(),nomeProduto_ProdutoPage.toUpperCase());
        assertEquals(precoProduto_HomePage.toUpperCase(), precoProduto_ProdutoPage.toUpperCase());
    }

    @Test
    public void LoginValido() {
        homepage = new HomePage(driver);

        //Clicar no botão Sign In na Homepage
        loginPage = homepage.clicarBotaoSignIn();

        //Preencher campos
        loginPage.preencherEmail("acedospunhosbemquentinho@gmail.com");
        loginPage.preencherSenha("BarbaBranca<3");

        //Clicar no botão Sign In para logar
        loginPage.clicarBotaoSignIn();

        //Validar se o usuário está logado corretamente
        assertTrue(homepage.estaLogado("Portgas D. Ace"));

        //Voltar página inicial
        driver.findElement(By.cssSelector("[alt='Loja de Teste']")).click();
    }

    @Test
    public void IncluirProdutoAoCarrinho() {
        homepage = new HomePage(driver);
        produtoPage = new ProdutoPage(driver);
        modalProdutoPage  = new ModalProdutoPage(driver);

        String tamanhoProduto = "L";
        String corProduto = "Black";
        int quantidadeProduto = 2;

        //--Pré-condição
        //Usuário logado
        if(!homepage.estaLogado("Portgas D. Ace")) {
            LoginValido();
        }

        //--Teste
        //Selecionar Produto
        DetalhesProduto();

        //Selecionar Tamanho
        List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();

        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        produtoPage.selecionarOpcaoDropdown(tamanhoProduto);
        listaOpcoes = produtoPage.obterOpcoesSelecionadas();

        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        //Selecionar Cor
        produtoPage.alterarCor();

        //Selecionar quantidade
        produtoPage.alterarQuantidade(Integer.toString(quantidadeProduto));

        //Adicionar ao Carrinho
        ModalProdutoPage modalProdutoPage = produtoPage.clicarAddToCart();

        //Validações
        assertTrue(modalProdutoPage.obterMensagemProdutoAdcionado().endsWith("Product successfully added to your shopping cart"));
        assertEquals(modalProdutoPage.obterTamanhoProduto(), tamanhoProduto);
        assertEquals(modalProdutoPage.obterCorProduto(), corProduto);
        assertEquals(modalProdutoPage.obterQuantidadeProduto(),Integer.toString(quantidadeProduto));

        assertEquals(modalProdutoPage.obterDescricaoProduto().toLowerCase(), nomeProduto_ProdutoPage.toLowerCase());

        String precoProdutoString = modalProdutoPage.obterPrecoProduto();
        precoProdutoString = precoProdutoString.replace("$","");
        Double precoProduto = Double.parseDouble(precoProdutoString);
        System.out.println("Preço produto = " + precoProduto);

        String subtotalString = modalProdutoPage.obterSubtotal();
        subtotalString = subtotalString.replace("$","");
        Double subtotal = Double.parseDouble(subtotalString);
        System.out.println("Preço subtotal = " + subtotal);

        Double subtotalCalculado = quantidadeProduto * precoProduto;
        System.out.println(subtotalCalculado);
        assertEquals(subtotal, subtotalCalculado);

    }

    //Valores Esperados
    String esperado_nomeProduto = "Hummingbird printed t-shirt";
    Double esperado_precoProduto = 19.12;
    String esperado_tamanhoProduto = "L";
    String esperado_corProduto = "Black";
    int esperado_input_quantidadeProduto = 2;
    Double esperado_subtotalProduto = esperado_precoProduto * esperado_input_quantidadeProduto;

    int esperado_numeroItensTotal = esperado_input_quantidadeProduto;
    Double esperado_subtotalTotal = esperado_subtotalProduto;
    Double esperado_shippingTotal = 7.00;
    Double esperado_totalTaxExclTotal = esperado_subtotalTotal + esperado_shippingTotal;
    Double esperado_totalIncTotal = esperado_totalTaxExclTotal;
    Double esperado_taxes = 0.00;

    String esperado_nomeCliente = "Portgas D. Ace";
    String esperado_email = "acedospunhosbemquentinho@gmail.com";

    @Test
    public void IrParaCarrinho() {
        //--Pré-condições
        //Produto incluido na tela ModalProdutoPage
        homepage = new HomePage(driver);
        produtoPage = new ProdutoPage(driver);
        modalProdutoPage  = new ModalProdutoPage(driver);

        IncluirProdutoAoCarrinho();
        carrinhoPage = modalProdutoPage.clicarBotaoProceedToCheckout();

        //Testes
        //Validar todos os elementos
        System.out.println("*** TELA DO CARRINHO ***");

        System.out.println(carrinhoPage.obter_nomeProduto());
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_precoProduto()));
        System.out.println(carrinhoPage.obter_tamanhoProduto());
        System.out.println(carrinhoPage.obter_corProduto());
        System.out.println(carrinhoPage.obter_input_quantidadeProduto());
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subtotalProduto()));

        System.out.println("** ITENS TOTAIS **");

        System.out.println(Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_numeroItensTotal()));
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subtotalTotal()));
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_shippingtotal()));
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxExclTotal()));
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxIncTotal()));
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_taxes()));

        //Asserções
        assertEquals(esperado_nomeProduto, carrinhoPage.obter_nomeProduto());
        assertEquals(esperado_precoProduto, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_precoProduto()));
        assertEquals(esperado_tamanhoProduto, carrinhoPage.obter_tamanhoProduto());
        assertEquals(esperado_corProduto, carrinhoPage.obter_corProduto());
        assertEquals(esperado_input_quantidadeProduto, Integer.parseInt(carrinhoPage.obter_input_quantidadeProduto()));
        assertEquals(esperado_subtotalProduto, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subtotalProduto()));

        assertEquals(esperado_numeroItensTotal,Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_numeroItensTotal()));
        assertEquals(esperado_subtotalTotal, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subtotalTotal()));
        assertEquals(esperado_shippingTotal, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_shippingtotal()));
        assertEquals(esperado_totalTaxExclTotal, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxExclTotal()));
        assertEquals(esperado_totalIncTotal, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxIncTotal()));
        assertEquals(esperado_taxes, Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_taxes()));
    }

    @Test
    public void IrparaCheckout() {
        //Pré condições
        //Produto disponível no carrinho de compras
        IrParaCarrinho();

        //Teste

        //Clicar no botão
        checkoutPage = carrinhoPage.IrparaCheckoutPage();
        //Preencher campos
        //Validar informações
        assertEquals(esperado_totalIncTotal, Funcoes.removeCifraoDevolveDouble(checkoutPage.obter_totalTaxIncTotal()));
        //assertEquals(esperado_nomeCliente.toLowerCase(), checkoutPage.obter_nomeCliente().toLowerCase());
        assertTrue(checkoutPage.obter_nomeCliente().startsWith(esperado_nomeCliente));
        checkoutPage.clicarBotaoConfirmAddress();

        String encontrado_shippingValor = checkoutPage.obter_shippingValor();
        encontrado_shippingValor = Funcoes.removeTexto(encontrado_shippingValor, " tax excl.");
        Double encontrado_shippingValor_Double = Funcoes.removeCifraoDevolveDouble(encontrado_shippingValor);
        assertEquals(encontrado_shippingValor_Double, esperado_shippingTotal);
        checkoutPage.clicarBotaoContinueShipping();

        //Selecionar opção "Pay By Check"
        checkoutPage.selecionarRadioPayByCheck();

        //Validar valor do cheque (amount)
        String encontrado_amountPayByCheck = checkoutPage.obter_amountPayByCheck();
        encontrado_amountPayByCheck = Funcoes.removeTexto(encontrado_amountPayByCheck, " (tax incl.)");
        Double encontrado_amountPyByCheck_Double = Funcoes.removeCifraoDevolveDouble(encontrado_amountPayByCheck);
        assertEquals(encontrado_amountPyByCheck_Double, esperado_totalIncTotal);

        //Clicar no opção "I agree"
        checkoutPage.clicarCheckboxIAgree();
        assertTrue(checkoutPage.estaSelecionadoCheckboxIAgree());
    }

    @Test
    public void finalizarPedido() {
        //Pré-Condições
        //Checkout completamente concluído
        IrparaCheckout();

        //Testes
        //Clicar no botão para confirmar o pedido
        pedidoPage = checkoutPage.clicarParaConfirmarPedido();
        //Validar valores na tela
        assertTrue(pedidoPage.obter_textoPedidoConfirmado().toUpperCase().endsWith("YOUR ORDER IS CONFIRMED"));
        assertEquals(esperado_email, pedidoPage.obter_email());
        assertEquals(esperado_subtotalProduto,pedidoPage.obter_totalProduto());
        assertEquals(esperado_totalIncTotal, pedidoPage.obter_totalTaxIncl());
        assertEquals("check", pedidoPage.obter_metodoPagamento());
    }

}
