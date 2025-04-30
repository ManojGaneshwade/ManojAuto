package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class LogInTest {
	
	static WebDriver driver=new ChromeDriver();;
	
	@Test(priority=1)
	public void login() throws InterruptedException
	{
		
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(@type,'submit')]")).click();
		
		
		String currentURL=driver.getCurrentUrl();
		String expectedURL="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		Assert.assertEquals(expectedURL, currentURL);
		
	}
	
	@Ignore
	@Test(priority=2)
	public void logout() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(@class,'oxd-userdropdown-tab')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		
		String currentURL=driver.getCurrentUrl();
		String expect="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		Assert.assertEquals(expect, currentURL);
	}

}
