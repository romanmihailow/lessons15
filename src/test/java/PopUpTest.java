import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PopUpTest {

    public static WebDriver driver;
    public static CookiePopUp cookiePopUp;

    @BeforeClass
    public static void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        cookiePopUp = new CookiePopUp(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Открываем страницу, где нужно проверить поп-ап
        driver.get(ConfProperties.getProperty("loginpage"));
    }



    @Test
    public void testCookiePopUpTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(cookiePopUp.cookiePageTitle));
        // Проверяем, что заголовок поп-ап окна соответствует ожидаемому тексту
        String expectedTitle = "Обработка файлов cookie";
        String actualTitle = cookiePopUp.shouldTitleUpPopUpWindow();

        // Используем Assert для проверки
        Assert.assertEquals("Заголовок поп-ап окна не соответствует ожидаемому тексту", expectedTitle, actualTitle);
    }

    @Test
    public void testClickButtonPopUpWindow() {
        //cookiePopUp.shouldTitleUpPopUpWindow();
        cookiePopUp.clickCookieAgreeButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2")));


        //Thread.sleep(2000)

        String expectedTitle = "Онлайн пополнение без комиссии";
        String actualTitle = paySectionTitle.getText().replace("\n", " ").trim();
        Assert.assertEquals("Заголовок поп-ап окна не соответствует ожидаемому тексту", expectedTitle, actualTitle);

    }



}
