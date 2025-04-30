package practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//create employee, jobtitle sorted, reset
public class AddEmployee {
	static WebDriver driver=new ChromeDriver();
	
	@Ignore
	@Test(priority = 3)
	public void createNewEmp() throws InterruptedException
	{
		/*
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(1000);
		
		
		//login to app-----------------
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(@type,'submit')]")).click();
		Thread.sleep(1000);
		
		
		
		//click on PIM------and fill form--------------
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		
		*/
		
		Thread.sleep(1000);//click on add emp
		driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofDays(10));
		WebElement firstname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
		firstname.sendKeys("priya");
		
		Thread.sleep(1000);
		WebElement middlename=driver.findElement(By.name("middleName"));
		middlename.sendKeys("prakash");
		
		Thread.sleep(1000);
		WebElement lastname=driver.findElement(By.name("lastName"));
		lastname.sendKeys("patil");
		
		Thread.sleep(1000);
		WebElement empid=driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input"));
		empid.clear();
		empid.sendKeys("9999");
		
		Thread.sleep(1000);
		WebElement savebtn=driver.findElement(By.xpath("//button[text()=' Save ']"));
		savebtn.click();
		
		boolean succmsg=driver.findElement(By.xpath("//p[text()='Success']")).isDisplayed();
		Assert.assertTrue(succmsg);
		
		//back to emp list
		Thread.sleep(1000);
		WebElement emplist=wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Employee List")));
		emplist.click();
		
	}
	

//check job title drop down are sorted or not
	
	@Test(priority = 1)
	public void jobTitleSorted() throws InterruptedException
	{
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(1000);
		
		
		//login to app-----------------
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(@type,'submit')]")).click();
		Thread.sleep(1000);
		
		
		
		//click on PIM--------------------
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		
		//click on dropdown
		WebElement drop= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div[1]/div[2]"));
		drop.click();
		
		//extract all webElements
		Thread.sleep(2000);
		List<WebElement> actualJobTitle=null;
		
		for(int i=0;i<3;i++)
		{
			try 
			{
				actualJobTitle=driver.findElements(By.xpath("//div[@role='option']"));
				break;
			}
			catch(StaleElementReferenceException e)
			{
				Thread.sleep(1000);
			}
		}
		
		List<String> title=new ArrayList<>();
		for(WebElement w:actualJobTitle)
		{
			title.add(w.getText());
		}
		System.out.println("title= "+title);
		
		//store in sorted list and sort in ascending order
		List<String> sortedTitle=new ArrayList<String>(title);
		Collections.sort(sortedTitle,String.CASE_INSENSITIVE_ORDER);
		
		System.out.println("sortedTitle= "+sortedTitle);
		Assert.assertEquals(title,sortedTitle);
		
	}
	
	@Test(priority = 2)
	public void resetFunctionality() throws InterruptedException
	{
		//send empname
		Thread.sleep(1000);
		WebElement empname=driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input"));
		empname.sendKeys("karan");
		
		System.out.println("empname===="+ empname.getDomProperty("value"));
		
		//send empid
		Thread.sleep(1000);
		WebElement empid= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input"));
		empid.sendKeys("kk132");
		
		//select jobtitle
		WebElement jobTitle=driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div/div[1]"));
		jobTitle.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),'Account Assistant')]")).click();
		
		//click reset button
		Thread.sleep(1000);
		WebElement resetbtn= driver.findElement(By.xpath("//button[@type='reset']"));
		resetbtn.click();
		
		
		
		
	//	System.out.println("after reset jobtitle value===="+jobTitle.getDomProperty("value")); will not work
		
		
		System.out.println("after reset empname===="+empname.getDomProperty("value"));
		System.out.println("after reset empid===="+empid.getDomProperty("value"));
		System.out.println("after reset jobtitle gettext===="+jobTitle.getText());
		
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(empname.getDomProperty("value"), "");
		soft.assertEquals(empid.getDomProperty("value"), "");
		soft.assertEquals(jobTitle.getText(),"-- Select --");
		soft.assertAll();
		
		
		
		
	}

}
