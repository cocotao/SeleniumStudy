package seleniumStudy.mafengwo;

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



public class MaFengWoAU {

	private int REFRESH_COUNT = 10;
	private int CHROME_TAB_NUM_MAX = 7;

	public WebDriver getWebDriver() {
		WebDriver driver = null;

		String ChromePath = ".//ChromeDriver//chromedriver_77_mac";
		System.setProperty("webdriver.chrome.driver", ChromePath);

		driver = new ChromeDriver(getChromeCapabilities());

		return driver;
	};

	private DesiredCapabilities getChromeCapabilities() {
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
		return capabilities;
	}

	public void refreshPageOnce(WebDriver driver, int refreshNum) throws Exception {
//		String baseUrl = "http://www.mafengwo.cn";
		String baseUrl = "http://www.mafengwo.cn/search/q.php?q=%E9%A3%8E%E5%85%89%E5%A4%A7%E6%B4%8B%E8%B7%AF%20%E8%90%8C%E5%AE%A0%E8%A2%8B%E9%BC%A0%E5%B2%9B%20%E5%8D%97%E6%BE%B3%E8%87%AA%E9%A9%BE%E6%B8%B8";
		driver.get(baseUrl);
		System.out.println("enter the ma feng wo portal " + refreshNum);

	//	driver.findElement(By.xpath("//*[@placeholder='搜目的地/攻略/酒店/旅行特价']")).sendKeys("风光大洋路 萌宠袋鼠岛 南澳自驾春游记");
	//	driver.findElement(By.xpath("//div[contains(@id, 'search_btn_all')]/a")).click();
	//	driver.findElement(By.xpath(""));

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@data-search-category='notes']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@href, '17249458')]")).click();
	}

	public void refreshPage(WebDriver driver) throws Exception {
		int i = 0, j = 0;
		for (; i < REFRESH_COUNT; i++) {
			refreshPageOnce(driver, i);
			j++;
			if (j == CHROME_TAB_NUM_MAX) {
				closeDriver(driver);
			}
		}
		closeDriver(driver);
	}

	public void closeDriver(WebDriver driver) throws Exception {
		driver.close();
		driver.quit();
	}
}