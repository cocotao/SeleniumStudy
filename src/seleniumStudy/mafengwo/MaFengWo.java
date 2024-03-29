package seleniumStudy.mafengwo;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MaFengWo {
	public static void main(String[] args) {
		WebDriver driver = null;
		
		String ChromePath = ".//ChromeDriver//chromedriver_macos";
		System.setProperty("webdriver.chrome.driver", ChromePath);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--use-fake-device-for-media-stream");
		options.addArguments("--use-fake-ui-for-media-stream");
		options.addArguments("--start-maximized");
		options.addArguments("--lang=en");
		options.addArguments("-–new-tab");
		
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		int i = 0, j = 0;
		
		// TODO: change the counts
		retry:
		for (i = 1; i <= 2; i++) {
			try {
				String baseUrl = "http://www.mafengwo.cn";
				driver.get(baseUrl);
				System.out.println("enter the mafeng wo portal");
			
				// TODO: change the article title text
				driver.findElement(By.xpath("//*[@placeholder='搜目的地/攻略/酒店/旅行特价']")).sendKeys("加拿大 班夫 纯美浪漫");
		        driver.findElement(By.xpath("//div[contains(@id, 'search_btn_all')]/a")).click();
		        // TODO: change the article id 
		        driver.findElement(By.xpath("//a[contains(@href, '10904630')]")).click();
			} catch (Exception e) {
				driver.close();
	        	driver.quit();
	        	
	        	j = 0;
	        	driver = new ChromeDriver(capabilities);
	        	continue retry;
			}
	        
	        System.out.println("test counts: " + i);
	        
	        j++;
	        if (j == 30) {
	        	driver.close();
	        	driver.quit();
	        	
	        	j = 0;
	        	driver = new ChromeDriver(capabilities);
	        }
		}
		
		driver.close();
		driver.quit();
		
		System.out.println("test end");
		System.exit(0);
	}
}