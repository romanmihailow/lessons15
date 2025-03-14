import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PaySectionTest {

    public static WebDriver driver;
    public static PaySectionPage paySectionPage;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        paySectionPage = new PaySectionPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Открываем страницу с платежными системами
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void testPaymentSystemLogos() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ждем, что изображения будут видны
        wait.until(ExpectedConditions.visibilityOf(paySectionPage.getPaySectionImageVisa()));
        wait.until(ExpectedConditions.visibilityOf(paySectionPage.getPaySectionImageVerifiedByVisa()));
        wait.until(ExpectedConditions.visibilityOf(paySectionPage.getPaySectionImageMasterCard()));
        wait.until(ExpectedConditions.visibilityOf(paySectionPage.getPaySectionImageMasterCardSecureCode()));
        wait.until(ExpectedConditions.visibilityOf(paySectionPage.getPaySectionImageBelCart()));

        // Проверяем, что все логотипы видны
        Assert.assertTrue("Логотип Visa не отображается", paySectionPage.getPaySectionImageVisa().isDisplayed());
        Assert.assertTrue("Логотип VerifiedByVisa не отображается", paySectionPage.getPaySectionImageVerifiedByVisa().isDisplayed());
        Assert.assertTrue("Логотип MasterCard не отображается", paySectionPage.getPaySectionImageMasterCard().isDisplayed());
        Assert.assertTrue("Логотип MasterCardSecureCode не отображается", paySectionPage.getPaySectionImageMasterCardSecureCode().isDisplayed());
        Assert.assertTrue("Логотип BelCart не отображается", paySectionPage.getPaySectionImageBelCart().isDisplayed());
    }
}
