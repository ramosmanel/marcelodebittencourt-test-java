package ecommercemarcelodebittencourt.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


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

    public void selecionarOpcaoDropdown(String opcao) {
        encontrarDropdownSize().selectByVisibleText(opcao);
    }

    public List<String> obterOpcoesSelecionadas() {
        List<WebElement> elementosSelecionados = encontrarDropdownSize().getAllSelectedOptions();

        List<String> listaOpcoesSelecionadas = new ArrayList();
        for (WebElement elemento : elementosSelecionados) {
            listaOpcoesSelecionadas.add(elemento.getText());
        }
        return listaOpcoesSelecionadas;
    }

    public Select encontrarDropdownSize() {
        return new Select(driver.findElement(By.id("group_1")));
    }

    public void alterarCor() {
         driver.findElement(By.xpath("//input[@value='11']")).click();
    }

    public void alterarQuantidade(String quantidade) {
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys(quantidade);
    }
    public ModalProdutoPage clicarAddToCart() {
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        return new ModalProdutoPage(driver);
    }
}

