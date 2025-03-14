import org.junit.AfterClass;
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

public class PaySectionFormTest {
    public static WebDriver driver;
    public static PaySectionForm paySectionForm;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        paySectionForm = new PaySectionForm(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Проверяем, есть ли кнопка согласия с cookie
        try {
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cookie-agree\"]")));
            cookieAgreeButton.click();
        } catch (Exception e) {
            System.out.println("Cookie button not found, continuing with the test.");
        }

        // Заполнение формы
        paySectionForm.clickPhoneNumberField();
        paySectionForm.sendPhoneNumber(ConfProperties.getProperty("phonenumber"));
        paySectionForm.clickSumField();
        paySectionForm.sendSum(ConfProperties.getProperty("sum"));
        paySectionForm.clickEmailField();
        paySectionForm.sendEmail(ConfProperties.getProperty("email"));
        paySectionForm.clickPayButton();

        // Добавим небольшую задержку перед поиском iframe
        try {
            Thread.sleep(2000);  // 2 секунды ожидания
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Явное ожидание появления iframe
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(iframe);

        // Добавляем задержку перед поиском элемента внутри iframe
        try {
            Thread.sleep(2000);  // 2 секунды ожидания
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Явное ожидание появления элемента внутри iframe
        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Оплата')]")));

        // Проверка заголовка
        String expectedTitle = "Оплата: Услуги связи Номер:375297777777";
        String actualTitle = paySectionTitle.getText().replace("\n", " ").trim();
        Assert.assertEquals("Тест провален", expectedTitle, actualTitle);
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
