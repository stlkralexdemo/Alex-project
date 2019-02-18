package ru.itpark.alexproject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itpark.alexproject.entity.MTBSize;
import ru.itpark.alexproject.entity.ProductEntity;
import ru.itpark.alexproject.entity.ProductType;
import ru.itpark.alexproject.repository.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlexProjectApplicationTests {

    private static WebDriver webDriver;

    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository repository;

    @BeforeAll
    public static void beforeAll() {

        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();

        webDriver = new FirefoxDriver(options);
    }

    @AfterAll
    public static void afterAll() {
    }

    @Test
    public void displaysMainPage() {
        webDriver.get("http://localhost:" + port);

        webDriver.findElement(By.className("item-search"));
    }
    @Test
    @DirtiesContext
    public void searchByName() {
        repository.saveAll(List.of(
                new ProductEntity(0,"Focus",89500,"Best bike", MTBSize.L,null,null, ProductType.MTB,null),
                new ProductEntity(0,"Cannondale",159500,"Pro bike", MTBSize.M,null,null, ProductType.MTB,null)

        ));

        webDriver.get("http://localhost:" + port);
        WebElement search = webDriver.findElement(By.className("item-search"));
        search.sendKeys("focus");
        search.sendKeys(Keys.ENTER);

        var wait = new WebDriverWait(webDriver, 5); // ожидание - max 5 сек

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.className("item"),
                1
        ));

        assertTrue(
                webDriver.findElement(By.className("item"))
                        .getText().toLowerCase()
                        .contains("focus")
        );
    }

    @Test
    @DirtiesContext
    public void searchByScottName() {
        repository.saveAll(List.of(
                new ProductEntity(0,"Focus",89500,"Best bike", MTBSize.L,null,null, ProductType.MTB,null),
                new ProductEntity(0,"Scott",159500,"Pro bike", MTBSize.M,null,null, ProductType.MTB,null)

        ));

        webDriver.get("http://localhost:" + port);
        WebElement search = webDriver.findElement(By.className("item-search"));
        search.sendKeys("Scott");
        search.sendKeys(Keys.ENTER);

        var wait = new WebDriverWait(webDriver, 5);

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.className("item"),
                1
        ));

        assertTrue(
                webDriver.findElement(By.className("item"))
                        .getText().toLowerCase()
                        .contains("scott")
        );
    }

}

