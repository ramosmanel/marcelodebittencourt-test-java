package ecommercemarcelodebittencourt.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class HomePage {
    private WebDriver driver;
    private List<WebElement> listaProdutos;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public int contarProdutos(int produtos) {
        listaProdutos = driver.findElements(By.className("product-description"));
        System.out.println("Número de produtos: " + listaProdutos.size());

        boolean result = false;
            if(listaProdutos.size() == produtos) {
                result = true;
            }
        Assert.assertTrue(result);
        return produtos;
    }

    public int carrinhoVazio(int carrinhoValor) {
        String valorCarrinho = driver.findElement(By.className("cart-products-count")).getText();
        valorCarrinho = valorCarrinho.replace("(", "");
        valorCarrinho = valorCarrinho.replace(")", "");
        int valorDoCarrinhoInt = Integer.parseInt(valorCarrinho);
        System.out.println(valorDoCarrinhoInt);

        boolean result = false;
        if(valorDoCarrinhoInt == carrinhoValor ) {
            System.out.println("Teste Aprovado!");
            result = true;
        }
        Assert.assertTrue(result);
        return carrinhoValor;
    }

    public String obterNomeProduto(int indice) {
            return driver.findElements(By.cssSelector(".product-description a")).get(indice).getText();
    }

    public String obterPrecoProduto(int indice) {
        return driver.findElements(By.className("price")).get(indice).getText();
    }

    public ProdutoPage clicarProduto(int indice) {
        driver.findElements(By.cssSelector(".product-description a")).get(indice).click();
        return new ProdutoPage(driver);

    }

    public LoginPage clicarBotaoSignIn() {
        driver.findElement(By.cssSelector("#_desktop_user_info span.hidden-sm-down")).click();
        return new LoginPage(driver);
    }

    public boolean estaLogado(String texto) {
        return texto.contentEquals(driver.findElement(By.cssSelector("#_desktop_user_info span.hidden-sm-down")).getText());
    }

    public void carregarPaginaInicial() {
        driver.get("https://marcelodebittencourt.com/demoprestashop/");
    }

    public String obterTituloPagina() {
        return driver.getTitle();
    }

    public boolean estaLogado() {
        return !"Sign in".contentEquals(driver.findElement(By.cssSelector("#_desktop_user_info span.hidden-sm-down")).getText());
    }

}
