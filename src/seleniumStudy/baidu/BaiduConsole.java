package seleniumStudy.baidu;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaiduConsole {
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
		options.addArguments("--auto-open-devtools-for-tabs");
		
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		
		try {
			String baseUrl = "http://www.baidu.com";
			driver.get(baseUrl);
			System.out.println("enter the baidu portal");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("console.log('Hello, Chrome console from selenium');");
			driver.findElement(By.xpath("//a[contains(@href, '10904630')]")).click();
		} catch (Exception e) {
			driver.close();
        	driver.quit();
		}
		
		System.out.println("test end");
		System.exit(0);
	}
}