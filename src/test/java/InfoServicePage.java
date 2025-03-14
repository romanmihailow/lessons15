import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public class InfoServicePage {
    public WebDriver driver;

    public InfoServicePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /// Кнопка принять (всплывающее окно при входе на страницу) - click
    @FindBy(xpath = "//*[@id=\"cookie-agree\"]")
    private WebElement cookieAgreeButton;

    /// Подробнее о сервисе - click
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a")
    private WebElement clickPaySectionLink;

    /// Проверка перехода == "Оплата банковской картой"
    @FindBy(xpath = "/html/body/div[6]/main/div/div[4]/h3[1]")
    private WebElement titlePageInfoPayCard;

    /// Метод клик по кнопке ПРИНЯТЬ в "Pop-up окне cookie"
    public void clickCookieAgreeButton() {
        cookieAgreeButton.click();
    }

    /// Метод для клика по ссылке подробнее об оплате
    public void clickPaySectionLink() {
        clickPaySectionLink.click();
    }

    /// Метод для получения title на странице информации об оплате
    public String getTitlePageInfoPayCard() {
        return titlePageInfoPayCard.getText();
    }






}
