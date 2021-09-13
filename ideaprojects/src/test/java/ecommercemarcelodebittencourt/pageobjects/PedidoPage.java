package ecommercemarcelodebittencourt.pageobjects;

import ecommercemarcelodebittencourt.util.Funcoes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PedidoPage {
    private WebDriver driver;
    public PedidoPage(WebDriver driver){
        this.driver = driver;
    }

    public String obter_textoPedidoConfirmado() {
        return driver.findElement(By.cssSelector("#content-hook_order_confirmation h3")).getText();
    }

    public String obter_email() {
        String texto = driver.findElement(By.cssSelector("#content-hook_order_confirmation p")).getText();
        texto = Funcoes.removeTexto(texto, "An email has been sent to the ");
        texto = Funcoes.removeTexto(texto, " address.");
        return texto;
    }

    public Double obter_totalProduto() {
        return Funcoes.removeCifraoDevolveDouble(driver.findElement(By.cssSelector("div.order-confirmation-table div.order-line div.row div.bold")).getText());
    }

    public Double obter_totalTaxIncl() {
        return Funcoes.removeCifraoDevolveDouble(driver.findElement(By.cssSelector("div.order-confirmation-table table tr.total-value td:nth-child(2)")).getText());
    }

    public String obter_metodoPagamento() {
        return Funcoes.removeTexto(driver.findElement(By.cssSelector("#order-details ul li:nth-child(2)")).getText(),"Payment method: Payments by ");
    }
}
