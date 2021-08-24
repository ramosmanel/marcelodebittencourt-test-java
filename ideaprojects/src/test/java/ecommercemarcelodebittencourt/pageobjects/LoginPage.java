package ecommercemarcelodebittencourt.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherEmail(String texto) {
        driver.findElement(By.name("email")).sendKeys(texto);
    }
    public void preencherSenha(String texto) {
        driver.findElement(By.name("password")).sendKeys(texto);
    }
    public void clicarBotaoSignIn() {
        driver.findElement(By.id("submit-login")).click();
    }
}
