import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaySectionPage {
    public WebDriver driver;

    public PaySectionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /// Visa ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[1]/img")
    private WebElement paySectionImageVisa;

    /// VerifiedByVisa ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[2]/img")
    private WebElement paySectionImageVerifiedByVisa;

    /// MasterCard ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[3]/img")
    private WebElement paySectionImageMasterCard;

    /// MasterCardSecureCode ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[4]/img")
    private WebElement paySectionImageMasterCardSecureCode;

    /// BelCart ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[5]/img")
    private WebElement paySectionImageBelCart;




    public WebElement getPaySectionImageVisa() {
        return paySectionImageVisa;
    }
    public WebElement getPaySectionImageVerifiedByVisa() {
        return paySectionImageVerifiedByVisa;
    }
    public WebElement getPaySectionImageMasterCard() {
        return paySectionImageMasterCard;
    }
    public WebElement getPaySectionImageMasterCardSecureCode() {
        return paySectionImageMasterCardSecureCode;
    }
    public WebElement getPaySectionImageBelCart() {
        return paySectionImageBelCart;
    }











}
