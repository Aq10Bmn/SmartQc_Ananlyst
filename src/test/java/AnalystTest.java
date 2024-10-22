import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class AnalystTest {
    WebDriver driver;
    boolean shouldLogout = false;

    @BeforeClass
    public void setup() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get("https://analyst.smartqc.io/");
        System.out.println("Setup completed");
    }

    @BeforeMethod
    public void login() throws InterruptedException {
        driver.get("https://analyst.smartqc.io/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        WebElement emailAddress = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='email']")));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='password']")));
        WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Log in']")));

        emailAddress.sendKeys("ashariff@smartqc.io");
        password.sendKeys("password@123");
        loginButton.click();
        Thread.sleep(10000);

        // Verify login success by checking the presence of a logout element or a specific URL
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='nav-item dropdown ']")));
        System.out.println("LOG-IN successful");
//        shouldLogout = true; // Set the flag to true after viewCampaign test
    }
@Test
//    @Test(dependsOnMethods = "login")
    public void Campaigns() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement Campaigns = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Campaigns']")));
        Campaigns.click();
        Thread.sleep(2000);
        System.out.println("Show All Campaigns");
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class='rounded-3 form-select']")));
        // Use JavaScript to ensure the dropdown is visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", dropdown);
        //Now locate the specific option
//        WebElement listBar20 = dropdown.findElement(By.xpath("//option[@value='20']"));
//        listBar20.click();
//        Thread.sleep(2000);
//        System.out.println("View 20 Campaign");
        //List of 10 Campaigns
        WebElement listBar40 = dropdown.findElement(By.xpath("//option[@value='40']"));
        listBar40.click();
        System.out.println("View 40 Campaign");
        //List of 20 Campaigns
//        WebElement listBar60 = dropdown.findElement(By.xpath("//option[@value='60']"));
//        listBar60.click();
//        System.out.println("View 60 Campaign");
        WebElement search = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        search.sendKeys("pending");
        Thread.sleep(10000);
        System.out.println("Search Successful");

//        WebElement BackButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Back']")));
//        BackButton.click();
//        Thread.sleep(2000);
//        shouldLogout = true; // Set the flag to true after Campaigns test
//
//    }
//
//    @Test(dependsOnMethods = "login")
//    public void ViewCampaign() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//        WebElement campaignsButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[3]/td[5]/a[1]/i[1]")));
//        campaignsButton.click();
//        Thread.sleep(2000);
//        System.out.println("Show All Campaigns");
        WebElement viewButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@class='bi bi-eye-fill'])[4]")));
        viewButton.click();
        Thread.sleep(2000);
        System.out.println("View Campaign Details");
        WebElement BackButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Back to Campaign']")));
        BackButton.click();
        Thread.sleep(2000);
        shouldLogout = true; // Set the flag to true after viewCampaign test
    }
@Test
//    @Test(dependsOnMethods = {"login", "Campaigns"})
    public void QualificationProcess() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        try {
            WebElement EmailCheck = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Email Check']")));
            EmailCheck.click();
//            Thread.sleep(1000);
            System.out.println("Email Check Successfully");
        } catch (Exception e) {
            System.out.println("Email Check not clickable, moving to next");
        }

        try {
            WebElement PersonCheck = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Person Check']")));
            PersonCheck.click();
//            Thread.sleep(1000);
            System.out.println("Person Check Successfully");
        } catch (Exception e) {
            System.out.println("Person Check not clickable, moving to next");
        }

        try {
            WebElement VoiceCallCheck = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Voice Call Check']")));
            VoiceCallCheck.click();
//            Thread.sleep(1000);
            System.out.println("Voice Call Check Successfully");
        } catch (Exception e) {
            System.out.println("Voice Call Check not clickable, moving to next");
        }

        try {
            WebElement CompanyCheck = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Company Check']")));
            CompanyCheck.click();
//            Thread.sleep(1000);
            System.out.println("Company Check Successfully");
        } catch (Exception e) {
            System.out.println("Company Check not clickable, moving to next");
        }


        WebElement Upload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Upload']")));
        Upload.click();
        Thread.sleep(2000);
        System.out.println("File import window open");

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        String filePath = "C:\\Users\\A-00714\\Desktop\\workspace\\Test_File_campaign_IBM_Campaign_1712235787140    .csv";
        fileInput.sendKeys(filePath);
        Thread.sleep(2000);
        System.out.println("File Upload Successfully");
        WebElement SubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-outline-success']")));
        SubmitButton.click();
//        Thread.sleep(1000);
        System.out.println("File Submitted");

        shouldLogout = true; // Set the flag to true after viewCampaign test

    }

    @AfterMethod
    void logout() throws InterruptedException {
        if (shouldLogout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

            // Locate and display the profile dropdown
            WebElement Profile = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='dropdownId']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Profile);
            Thread.sleep(2000); // Delay to observe the flow

            try {
                Profile.click();
            } catch (ElementClickInterceptedException e) {
                // Use JavaScript to click the element if it is intercepted
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Profile);
            }
            Thread.sleep(2000); // Delay to observe the flow

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.display='block';", Profile);

            // Wait for the logout button to be present and then click it
            WebElement LogoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dropdown-item fw-bold nav-item' and text()='Logout']")));
            Thread.sleep(2000); // Delay to observe the flow

            try {
                LogoutButton.click();
            } catch (ElementClickInterceptedException e) {
                // Use JavaScript to click the element if it is intercepted
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", LogoutButton);
            }
            Thread.sleep(2000); // Delay to observe the flow

            System.out.println("Log out Successfully");
            shouldLogout = false; // Reset the flag
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("driver closed");
        }
    }
}
