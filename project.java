package selenium;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class Project {

	    public static void main(String[] args) {

	        // Setup browser
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://opensource-demo.orangehrmlive.com/");

	        // Explicit Wait
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        try {
	            // Login
	            WebElement username = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.name("username"))
	            );
	            username.sendKeys("Admin");

	            driver.findElement(By.name("password")).sendKeys("admin123");
	            driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

	            System.out.println("Login successful");

	            // Click profile dropdown
	            WebElement profile = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//p[@class='oxd-userdropdown-name']")
	                )
	            );
	            profile.click();

	            // Click About
	            WebElement about = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//a[normalize-space()='About']")
	                )
	            );
	            about.click();

	            System.out.println("About popup opened");

	            // Get popup text
	            WebElement popup = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[@class='oxd-grid-2 orangehrm-about']")
	                )
	            );

	            System.out.println("Popup Text:\n" + popup.getText());
	            
	            //screenshot
	            File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	            FileUtils.copyFile(scr, new File("H:\\project\\About.png"));

	            // Close popup
	            WebElement closeBtn = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                    By.xpath("//button[@class='oxd-dialog-close-button oxd-dialog-close-button-position']")
	                )
	            );
	            closeBtn.click();

	            System.out.println("Popup closed");

	        } catch (Exception e) {
	            System.out.println("Test Failed: " + e.getMessage());
	        } finally {
	            driver.quit();
	        }
	    }
	}
