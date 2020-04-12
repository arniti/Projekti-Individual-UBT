package BLL;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ParseMovies {
    private  ArrayList<String> pershkrimet = new ArrayList<>();
    public void crawlDescriptions() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","C:\\WebDrivers\\geckodriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.filma24.to/");
        List<WebElement> elements = driver.findElements(By.xpath("(//div[@class='xtt'])"));
        for(int i =1;i<=elements.size();i++) {
            try {
                driver.findElement(By.xpath("(//div[@class='xtt'])[" + i + "]")).click();
                pershkrimet.add(driver.findElement(By.className("syn-wrapper")).getText());
            }catch (NoSuchElementException s) {continue;}
            driver.navigate().back();
            driver.navigate().refresh();
        }
        driver.close();
    }

    public ArrayList<String> getPershkrimet() {
        return pershkrimet;
    }

}
