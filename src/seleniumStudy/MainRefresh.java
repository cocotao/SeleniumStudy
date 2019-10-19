package seleniumStudy;

import org.openqa.selenium.WebDriver;
import seleniumStudy.mafengwo.MaFengWoAU;


public class MainRefresh {
    private final static int REFRESH_COUNT = 10;
    private final static int CHROME_TAB_NUM_MAX = 7;

    public static void main(String[] args) {
        MaFengWoAU maFengWoAU = new MaFengWoAU();
        WebDriver webDriver = null;
        webDriver = maFengWoAU.getWebDriver();

        int i = 0, j = 0;
        retry:
        for ( ; i < REFRESH_COUNT; i++) {
            if (webDriver != null) {
                try {
                    maFengWoAU.refreshPageOnce(webDriver, i);
                    j++;
                    if (j == CHROME_TAB_NUM_MAX) {
                        maFengWoAU.closeDriver(webDriver);
                    }
                } catch (Exception e) {
                    System.out.println("[Debug] Exceptions: " + e.toString());
                    try {
                        maFengWoAU.closeDriver(webDriver);
                        webDriver = maFengWoAU.getWebDriver();
                        continue retry;
                    } catch (Exception e2) {
                        System.out.println("[Debug] Exceptions: " + e2.toString());
                        webDriver = maFengWoAU.getWebDriver();
                        continue retry;
                    }
                }
            }
        }

        try {
            maFengWoAU.closeDriver(webDriver);
        } catch (Exception e) {
            System.out.println("[Debug] Exceptions: " + e.toString());
        }

        System.out.println("[Debug] " + "Test Complete!");
    }
}
