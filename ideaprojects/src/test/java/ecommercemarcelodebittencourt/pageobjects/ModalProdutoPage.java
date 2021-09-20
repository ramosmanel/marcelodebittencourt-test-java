package ecommercemarcelodebittencourt.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ModalProdutoPage {
    private WebDriver driver;
    private By listaValoresInformados = By.cssSelector("div.divide-right .col-md-6:nth-child(2) span strong");

    public ModalProdutoPage(WebDriver driver){this.driver = driver;}

    public String obterMensagemProdutoAdcionado() {
        FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModalLabel")));

        return driver.findElement(By.id("myModalLabel")).getText();
    }

    public String obterDescricaoProduto() {return driver.findElement(By.className("product-name")).getText();}

    public String obterPrecoProduto() {return driver.findElement(By.cssSelector("div.modal-body p.product-price")).getText();}

    public String obterTamanhoProduto() {return driver.findElements(By.cssSelector("div.divide-right .col-md-6:nth-child(2) span strong")).get(0).getText();}

    public String obterCorProduto() {
        if(driver.findElements(listaValoresInformados).size() == 3)
            return driver.findElements(listaValoresInformados).get(1).getText();
        else
            return "N/A";
    }

    public String obterQuantidadeProduto() {
        if(driver.findElements(listaValoresInformados).size() == 3)
            return driver.findElements(By.cssSelector("div.divide-right .col-md-6:nth-child(2) span strong")).get(2).getText();
        else
            return driver.findElements(listaValoresInformados).get(1).getText();
    }

    public String obterSubtotal(){return driver.findElement(By.cssSelector(".cart-content p:nth-child(2) span.value")).getText();}

    public CarrinhoPage clicarBotaoProceedToCheckout() {
       driver.findElement(By.cssSelector("div.cart-content-btn a.btn-primary")).click();
       return new CarrinhoPage(driver);
    }
}
