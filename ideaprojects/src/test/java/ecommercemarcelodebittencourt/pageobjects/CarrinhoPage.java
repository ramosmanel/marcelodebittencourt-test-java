package ecommercemarcelodebittencourt.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
By.cssSelector("div.product-line-info a"); - nome
By.cssSelector("span.price"); - preço
By.xpath("//div[contains(@class,'product-line-grid-body')]//div[3]/span[contains(@class.'value')]"); - tamanho
By.xpath("//div[contains(@class,'product-line-grid-body')]//div[4]/span[contains(@class.'value')]"); - cor
By.cssSelector("input.js-cart-line-product-quantity"); - Quantidade
By.cssSelector("span.product-price strong"); - subtotal
By.cssSelector("span.js-subtotal"); - Número de itens no total
By.cssSelector("#cart-subtotal-products span.value"); - Subtotal Total
By.cssSelector("#cart-subtotal-shipping span.value"); - Shipping total
By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(1) span.value"); - TotalTaxExclTotal
By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(2) span.value"); - TotalTaxIncTotal
By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(3) span.value"); - Taxes
 */

public class CarrinhoPage {
    private WebDriver driver;
    public CarrinhoPage(WebDriver driver){
        this.driver = driver;
    }

    public String obter_nomeProduto() {
        return driver.findElement(By.cssSelector("div.product-line-info a")).getText();
    }

    public String obter_precoProduto() {
        return driver.findElement(By.cssSelector("span.price")).getText();
    }

    public String obter_tamanhoProduto() {
        return driver.findElement(By.xpath("//span[.='L']")).getText();
    }

    public String obter_corProduto() {
        return driver.findElement(By.xpath("//span[.='Black']")).getText();
    }

    public String obter_input_quantidadeProduto() {
        return driver.findElement(By.cssSelector("input.js-cart-line-product-quantity")).getAttribute("value");
    }

    public String obter_subtotalProduto() {
        return driver.findElement(By.cssSelector("span.product-price strong")).getText();
    }

    public String obter_numeroItensTotal() {
        return driver.findElement(By.cssSelector("span.js-subtotal")).getText();
    }

    public String obter_subtotalTotal() {
        return driver.findElement(By.cssSelector("#cart-subtotal-products span.value")).getText();
    }

    public String obter_shippingtotal() {
        return driver.findElement(By.cssSelector("#cart-subtotal-shipping span.value")).getText();
    }

    public String obter_totalTaxExclTotal() {
        return driver.findElement(By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(1) span.value")).getText();
    }

    public String obter_totalTaxIncTotal() {
        return driver.findElement(By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(2) span.value")).getText();
    }

    public String obter_taxes() {
        return driver.findElement(By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(3) span.value")).getText();
    }

}
