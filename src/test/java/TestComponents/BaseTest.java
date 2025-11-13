package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	public WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void initializeDriver() throws IOException {

		boolean headLess = false;

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/Properties/Global.properties");
		prop.load(fis);

		String browser;
		String url;

		if (System.getProperty("browser") != null) {
			browser = System.getProperty("browser");
		} else {
			browser = prop.getProperty("browser");

		}
		if (System.getProperty("url") != null) {
			url = System.getProperty("browser");
		} else {
			url = prop.getProperty("url");
		}

		if (browser.toLowerCase().contains("chrome")) {

			ChromeOptions options = new ChromeOptions();
			if (browser.toLowerCase().contains("headless")) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				headLess = true;
			}
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		}

		if (!headLess) {
			driver.manage().window().maximize();
		}
		driver.get(url);
	}

	@AfterMethod(alwaysRun = true)
	public void quitDriver() {
		driver.quit();
	}

	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {

		String path = System.getProperty("user.dir") + "/Deliverables/Screenshots/" + testCaseName + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);

		FileUtils.copyFile(source, dest);

		return path;
	}

}
