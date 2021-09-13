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

    public String obter_shippingValor() {
        return driver.findElement(By.cssSelector("span.carrier-price")).getText();
    }

    public void clicarBotaoContinueShipping() {
        driver.findElement(By.name("confirmDeliveryOption")).click();
    }

    public void selecionarRadioPayByCheck() {
        driver.findElement(By.id("payment-option-1")).click();
    }

    public String obter_amountPayByCheck(){
        return driver.findElement(By.cssSelector
                ("#payment-option-1-additional-information > section > dl > dd:nth-child(2)")).getText();
    }

    public void clicarCheckboxIAgree() {
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
    }

    public boolean estaSelecionadoCheckboxIAgree() {
        return driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).isSelected();
    }

    public PedidoPage clicarParaConfirmarPedido() {
        driver.findElement(By.cssSelector("#payment-confirmation button")).click();
        return new PedidoPage(driver);
    }
}
