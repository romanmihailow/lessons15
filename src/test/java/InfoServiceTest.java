import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class InfoServiceTest {

    public static WebDriver driver;
    public static InfoServicePage infoServicePage;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        infoServicePage = new InfoServicePage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Открываем страницу, где нужно проверить ссылку информация о сервисе
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void testClickLinkInfoService() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Проверяем, есть ли кнопка согласия с cookie
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cookie-agree\"]")));
            if (cookieAgreeButton.isDisplayed()) {
                cookieAgreeButton.click();
            }
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем выполнение теста.");
        }

        // Кликаем на ссылку "Оплата"
        infoServicePage.clickPaySectionLink();

        // Ожидаем загрузки нужного элемента на странице
        WebElement titlePageInfoPayCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/main/div/div[4]/h3[1]")));

        // Проверяем, что заголовок страницы соответствует ожиданиям
        String expectedTitle = "Оплата банковской картой";
        String actualTitle = titlePageInfoPayCard.getText();
        Assert.assertEquals("Заголовок формы не соответствует", expectedTitle, actualTitle);
    }

}
