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

        try {
            // Проверяем, есть ли кнопка согласия с cookie
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cookie-agree\"]")));
            if (cookieAgreeButton.isDisplayed()) {
                cookieAgreeButton.click();
            }
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем выполнение теста.");
        }

        paySectionForm.clickPhoneNumberField();
        paySectionForm.sendPhoneNumber(ConfProperties.getProperty("phonenumber"));
        paySectionForm.clickSumField();
        paySectionForm.sendSum("100");
        paySectionForm.clickEmailField();
        paySectionForm.sendEmail("test@test.com");
        paySectionForm.clickPayButton();


        // Переключаемся на iframe с использованием id
        //driver.switchTo().frame("gpay-card-info-iframe.gpay-card-info-iframe-fade-in");


        driver.switchTo().frame(0);

        //driver.switchTo().frame(driver.findElement(By.tagName("gpay-card-info-iframe gpay-card-info-iframe-fade-in")));
        //driver.switchTo().frame("gpay-card-info-iframe gpay-card-info-iframe-fade-in");

        // Ожидаем загрузки нужного элемента на странице
        //WebElement titlePageInfoPayCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span")));

        paySectionForm.getPaySectionTitle();

        String expectedTitle = "Оплата: Услуги связи\n" +
                "Номер:375297777777";
        String actualTitle = paySectionForm.getPaySectionTitle();
        Assert.assertEquals("Тест провален", expectedTitle, actualTitle);


    }











//    @AfterClass
//    public static void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}

