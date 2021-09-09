package ecommercemarcelodebittencourt.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public String obter_totalTaxIncTotal() {
        return driver.findElement(By.cssSelector("div.cart-total span.value")).getText();
    }

    public String obter_nomeCliente() {
        return driver.findElement(By.cssSelector("div.address")).getText();
    }

    public void clicarBotaoConfirmAddress(){
        driver.findElement(By.name("confirm-addresses")).click();
    }
}
