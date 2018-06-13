package backend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RegsitrationWorker {

	// abstract the variable for future development
    static {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver-win.exe");
        System.setProperty("webdriver.chrome.driver", "chromedriver");
    }
    
    private static final RegsitrationWorker INSTANCE = new RegsitrationWorker();
    private WebDriver driver = new ChromeDriver();
    
    private RegsitrationWorker() {}
    
    public static RegsitrationWorker getInstance() {
    	return INSTANCE;
    }
    
    private void setup() {
        // login to minerva
        driver.get(BotConfiguration.MINERVA_URL);
        driver.findElement(By.id("mcg_un")).sendKeys(BotConfiguration.getEmail());
        driver.findElement(By.id("mcg_pw")).sendKeys(BotConfiguration.getPassword());
        driver.findElement(By.id("mcg_un_submit")).click();

        // student menu -> registration menu -> quick add drop
        driver.findElement(By.linkText("Student Menu")).click();
        driver.findElement(By.linkText("Registration Menu")).click();
        driver.findElement(By.linkText("Quick Add or Drop Course Sections")).click();

        // select the term
        Select term = new Select(driver.findElement(By.id("term_id")));
        term.selectByValue(BotConfiguration.getTerm());
        driver.findElement(By.className("pagebodydiv")).findElement(By.tagName("input")).click();
    }

    private void register() {
    	int counter = 1;
    	for(String crn: BotConfiguration.getCRNs()) {
    		driver.findElement(By.id("crn_id" + counter)).sendKeys(crn);
    	}
        driver.findElement(By.className("pagebodydiv")).findElement(By.xpath("//input[@name='REG_BTN' and @value='Submit Changes']")).click();
    }
}
