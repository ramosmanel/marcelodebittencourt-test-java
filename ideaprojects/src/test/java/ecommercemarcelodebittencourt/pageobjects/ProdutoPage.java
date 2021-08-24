package ecommercemarcelodebittencourt.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProdutoPage {
    private WebDriver driver;
    public ProdutoPage(WebDriver driver) {
        this.driver = driver;
    }
    public String obterNomeProduto() {
        return driver.findElement(By.className("h1")).getText();
    }
    public String obterPrecoProduto() {
        return driver.findElement(By.cssSelector(".current-price span:nth-child(1)")).getText();
    }
}
