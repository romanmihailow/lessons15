import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CookiePopUp {
    public WebDriver driver;

    public CookiePopUp(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /// Проверка pop-up она == "Обработка файлов cookie"
    @FindBy(xpath = "/html/body/div[6]/main/div/div[2]/div/div[1]/div[1]/h3")
    public WebElement cookiePageTitle;

    /// Кнопка принять (всплывающее окно при входе на страницу) - click
    @FindBy(xpath = "//*[@id=\"cookie-agree\"]")
    private WebElement cookieAgreeButton;

    /// Название блока онлайн пополнение без комиссии == "Онлайн пополнение без комиссии"
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2/text()[1]")
    private WebElement paySectionTitle;


    /// Метод запроса title "Pop-Up окна cookie"
    public String shouldTitleUpPopUpWindow() {
        return cookiePageTitle.getText();
    }
    /// Метод клик по кнопке ПРИНЯТЬ в "Pop-up окне cookie"
    public void clickCookieAgreeButton() {
        cookieAgreeButton.click();
    }
    public String getPaySectionTitle() {
        return paySectionTitle.getText();
    }









}
