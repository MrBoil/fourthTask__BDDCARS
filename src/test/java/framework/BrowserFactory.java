package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    private static WebDriver driver = null;

    private BrowserFactory() {
    }

    public static WebDriver instanceBrowserFactory() {
        if (driver == null) {
            driver = getDriver(DataFromJson.getBrowserName().toLowerCase(),
                    System.getProperty("os.name").toLowerCase().substring(0, 3));
            return managesWithDriver(driver);
        }
        return driver;
    }

    private static WebDriver getDriver(String browserName, String osName) {
        System.out.println(browserName + " " + osName);
        File downloadDir = new File("src/test/resources/downloads");
        switch (browserName) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("download.prompt_for_download", false);
                chromePrefs.put("download.default_directory", downloadDir.getAbsolutePath());
                chromePrefs.put("safebrowsing.enabled", true);

                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("disable-infobars");
                options.addArguments("disable-popup-blocking");
                options.addArguments("ignore-certificate-errors");

                switch (osName) {
                    case "mac":
                        System.setProperty("webdriver.chrome.driver", BrowserFactory.class.getResource("../chromedriver").getPath());
                        return new ChromeDriver(options);
                    case "win":
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator +"src\\test\\resources\\chromedriver.exe");
                        return new ChromeDriver(options);
                    default:
                        System.out.println(osName);
                        throw new IllegalArgumentException("Invalid OS");
                }

            case "firefox":
                FirefoxProfile profile=new FirefoxProfile();
                profile.setPreference("browser.download.dir", downloadDir.getAbsolutePath());
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
                profile.setAcceptUntrustedCertificates(true);
                switch (osName) {
                    case "mac":
                        System.setProperty("webdriver.gecko.driver", BrowserFactory.class.getResource("../geckodriver").getPath());
                        return new FirefoxDriver(profile);
                    case "win":
                        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator +"src\\test\\resources\\geckodriver.exe");
                        return new FirefoxDriver(profile);
                    default:
                        throw new IllegalArgumentException("Invalid OS");
                }
        }
        return driver;
    }

    private static WebDriver managesWithDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(DataFromJson.getImplicityWaitTime(), TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(DataFromJson.getSctiptTimeoutTime(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver driver() {
        return driver;
    }

}
