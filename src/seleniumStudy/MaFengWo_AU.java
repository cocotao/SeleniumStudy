package seleniumStudy;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class MaFengWo_AU {
	public static void main(String[] args) {
		try {
			tryToRefreshBrowserNum();
		} catch (Exception e) {
			System.out.println("[Debug Exception] " + e.toString());
		}
	}
	
	private static void tryToRefreshBrowserNum() throws NoSuchElementException, InterruptedException, Exception{
		WebDriver driver = null;
		
		String ChromePath = ".//ChromeDriver//chromedriver_77_mac";
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
		options.addArguments("--headless");
		
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		
		int j = 0;
		
		// TODO: change the counts
		for (int i = 1; i <= 4; i++) {
			String baseUrl = "http://www.mafengwo.cn";
			driver.get(baseUrl);
			System.out.println("enter the mafeng wo portal");
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@placeholder='搜目的地/攻略/酒店/旅行特价']")).sendKeys("风光大洋路 萌宠袋鼠岛 南澳自驾春游记");
	        driver.findElement(By.xpath("//div[contains(@id, 'search_btn_all')]/a")).click();
	        driver.findElement(By.xpath("//*[@id=\"_j_mfw_search_main\"]/div[1]/div/div/a[3]")).click();
	        driver.findElement(By.xpath("//a[contains(@href, '17249458')]")).click();
	        
	        System.out.println("test counts: " + i);
	        
	        j++;
	        if (j == 2) {
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