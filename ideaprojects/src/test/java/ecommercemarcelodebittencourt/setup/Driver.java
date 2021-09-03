package ecommercemarcelodebittencourt.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class Driver {
    public WebDriver WebDriver() {
        //Informar referência do driver...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pichau\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://marcelodebittencourt.com/demoprestashop/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
