package practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class duplicateCheckbox {

	@Ignore
	@Test(priority = 1)
	public void checkbox() throws InterruptedException
	{
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement username=  driver.findElement(By.name("username"));
		username.sendKeys("Admin");
		
		WebElement pass=driver.findElement(By.name("password"));
		pass.sendKeys("admin123");
		
		WebElement button=driver.findElement(By.cssSelector("button[type='submit']"));
		button.click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//span[text()='Admin']")).click();
		
	/*
	
	Symbol	Meaning
	/	Select direct child
	//	Select any descendant (child, grandchild, etc.)
	.	Current node
	
	*/
	
	 //take all rows 
		Thread.sleep(1000);
	List<WebElement> rows=driver.findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div"));
	Map<String,List<WebElement>> mp=new HashMap<>();
	
	for(WebElement w:rows)
	{
		String name=w.findElement(By.xpath("./div/div[3]")).getText();
		System.out.println(name);
		
		WebElement check=w.findElement(By.xpath("./div/div[1]"));
		
		if(mp.containsKey(name))
		{
			mp.get(name).add(check);
		}
		else
		{
			List<WebElement> checkboxes=new ArrayList<>();
			checkboxes.add(check);
			mp.put(name,checkboxes);
		}
	}
	
	
	for(Map.Entry<String,List<WebElement>> ent:mp.entrySet())
	{
		System.out.println("p");
		if(ent.getValue().size()>1)
		{
			
			for(WebElement x:ent.getValue())
			{
				if(!x.isSelected())
				{
					Thread.sleep(1000);
					x.click();
				}
			}
		}
	}
	

	}
	
	
	@Test(priority = 2)
	public void sugarCRM() throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://sg-vanillainstance.demo.sugarcrm.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.findElement(By.id("useridentifier")).sendKeys("admin");
		Thread.sleep(1000);
		driver.findElement(By.name("password")).sendKeys("Ambit@123");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Log In")).click();
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("document.body.style.zoom='80%'");
		
		Thread.sleep(1000);
		driver.findElement(By.id("Contacts_sidebar-nav-item")).click();
		
//		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-bs-original-title='Contacts']")));
//		
		Thread.sleep(2000);
		List<WebElement> rows= driver.findElements(By.xpath("//*[@id=\"content\"]/div/div/div[1]/div[3]/div[2]/div[2]/div[3]/div/div/table/tbody/tr"));
		Map<String,List<WebElement>> mp=new HashMap<>();
		
		for(WebElement row:rows)
		{
			String name= row.findElement(By.xpath("./td[2]")).getText();
			
			WebElement check=row.findElement(By.xpath(".//td[1]/span/div[1]/span/input"));
					
					if(mp.containsKey(name))
					{
						mp.get(name).add(check);
					}
					else
					{
						List<WebElement> l=new ArrayList<>();
						l.add(check);
						mp.put(name, l);
					}
		}
		
		for(Map.Entry<String,List<WebElement>> ent:mp.entrySet())
		{
			//System.out.println(ent.getKey()+"count = "+ent.getValue().size());
			
			if(ent.getValue().size()>1)
			{
				for(WebElement w:ent.getValue())
				{
					System.out.println(w);
					if(!w.isSelected())
					{
						Thread.sleep(1000);
						w.click();
					}
				}
			}
			
		}
		
		driver.close();
		
	
	}
	
	
	
	
}
