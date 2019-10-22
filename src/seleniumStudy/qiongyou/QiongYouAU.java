package seleniumStudy.qiongyou;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashSet;
import java.util.Set;

public class QiongYouAU {
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
       // options.addArguments("--headless");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    public void refreshPageOnce(WebDriver driver, int refreshNum) throws Exception {
//        String baseUrl = "http://www.qyer.com/";
        String baseUrl = "https://search.qyer.com/qp/?keyword=%E9%A3%8E%E5%85%89%E5%A4%A7%E6%B4%8B%E8%B7%AF";
        driver.get(baseUrl);
        System.out.println("enter the qiong you portal " + refreshNum);


//        driver.findElement(By.xpath("//div[contains(@class, 'tab')][2]")).click();
//        driver.findElement(By.xpath("//input[contains(@placeholder, '搜目的地/主题攻略/问题')]")).sendKeys("风光大洋路 萌宠袋鼠岛 南澳自驾春游记");
//        driver.findElement(By.xpath("//button[@data-v-49c4ab35][contains(@class, 'button-place')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(@href, '3318451')][@class='title']")).click();
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
