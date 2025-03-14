
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaySectionForm {
    public WebDriver driver;

    public PaySectionForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /// Поле номер телефона - ввести номер телефона: 297777777
    @FindBy(xpath = "//*[@id=\"connection-phone\"]")
    private WebElement connectionPhoneNumber;

    /// Поле сумма - ввести сумму: 100
    @FindBy(xpath = "//*[@id=\"connection-sum\"]")
    private WebElement connectionSum;

    /// Поле email - ввести email: rmw@gmail.com
    @FindBy(xpath = "//*[@id=\"connection-email\"]")
    private WebElement connectionEmail;

    /// Кнопка продолжить - click
    @FindBy(xpath = "//*[@id=\"pay-connection\"]/button")
    private WebElement payConnectionButton;

    /// Проверка перехода на форму оплаты
    @FindBy(xpath = "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span")
    private WebElement paySectionTitle;

    public void clickPhoneNumberField() {
        connectionPhoneNumber.click();
    }
    public void sendPhoneNumber(String phoneNumber) {
        connectionPhoneNumber.sendKeys("297777777");
    }
    public void clickSumField() {
        connectionSum.click();
    }
    public void sendSum(String sum) {
        connectionSum.sendKeys("100");
    }
    public void clickEmailField() {
        connectionEmail.click();
    }
    public void sendEmail(String email) {
        connectionEmail.sendKeys("rmw@gmail.com");
    }
    public void clickPayButton() {
        payConnectionButton.click();
    }
    public String getPaySectionTitle() {
        return paySectionTitle.getText();
    }








}