package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateOrderFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createOrder(ChromeDriver driver) throws Exception {
        driver.get(String.format("%s/%s", baseUrl, "order/create"));

        WebElement authorNameInput = driver.findElement(By.id("authorNameInput"));
        authorNameInput.sendKeys("Safira Sudrajat");
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys("Meja");
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys("10");
        WebElement submitButton = driver.findElement(By.name("submit_button"));
        submitButton.click();

        WebElement authorNameInput2 = driver.findElement(By.id("authorNameInput"));
        authorNameInput2.sendKeys("Safira Sudrajat");
        WebElement submitButton2 = driver.findElement(By.name("submit_button"));
        submitButton2.click();

        List<WebElement> allTdElements = driver.findElements(By.tagName("td"));
        WebElement myCreationElement = null;
        for (WebElement TdElement : allTdElements) {
            if (TdElement.getText().equals("Meja")) {
                myCreationElement = TdElement;
                break;
            }
        }

        assertNotNull(myCreationElement);
    }
}
