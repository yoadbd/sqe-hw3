package hellocucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.Random;

public class UserActuator {
    private WebDriver driver;
    private WebDriverWait wait;


    public void initSession(String webDriver, String path){

        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // launch website -> localhost
        driver.get("https://demo.simplcommerce.com/");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();
        checkLoggedOut();
        System.out.println("Driver setup finished for - " + driver.getTitle());



    }
    public void checkLoggedOut()
    {
        try {
            driver.findElement(By.xpath("/html/body/div[1]/div/ul/li[3]/form/button")).click();
        }
        catch (Exception ignored){ }
    }
    public void LogInTab()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log in"))).click();
    }
    public void LogIn(String mail, String pass)
    {

        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys(mail);

        // locate the password input box and enter password
        // $x("//*[@name='password' and @type='password']")
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(pass);

        // locate Log in button and press
        // $x("//*[@id='loginbtn']")
        driver.findElement(By.className("btn btn-primary")).click();
    }

    public void HomePage()
    {
        driver.get("https://demo.simplcommerce.com/");
    }
    public void addToCart()
    {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn btn-lg btn-add-cart"))).click();
        driver.findElement(By.xpath("className/html/body/div[6]/div/div/div[3]/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/a/img")).click();

    }
    public void clickOnProduct()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/div/a/img"))).click();
    }
    public boolean checkAdminLogged()
    {
        HomePage();
        try {
            driver.findElement(By.linkText("Hello Administrator!"));
            return true;
        }
        catch (Exception ignored){ return false;}
    }

    public void GoToDashboard()
    {
        //boolean logged_admin = checkAdminLogged();
            HomePage();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Dashboard"))).click();


    }
    public void GoToManagement()
    {
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[1]/a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Users"))).click();

    }
    public void SearchUser(String mail)
    {
        Select drpCountry = new Select(driver.findElement(By.xpath("/html/body/div[2]/div/table/thead/tr[2]/th[3]/div/select")));
        drpCountry.selectByValue("2");
        try {
            wait.wait(1000);
        }
        catch (Exception ignored){ }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/table/thead/tr[2]/th[1]/div/input"))).sendKeys(mail);



    }
    public void LogInSequence(String mail, String pass)
    {
        LogInTab();
        LogIn(mail, pass);
    }
    public void deleteUser(String mail)
    {
        SearchUser(mail);
        driver.findElement(By.xpath("/html/body/div[2]/div/table/tbody[1]/tr/td[6]/button")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]")).click();

    }

}
