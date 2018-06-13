package backend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationWorker {

    private static final RegistrationWorker INSTANCE = new RegistrationWorker();
    private RegistrationWorker() {}
    
    public static RegistrationWorker getInstance() {
    	return INSTANCE;
    }

    public void startRegistration() {
        WebDriver driver = new ChromeDriver();
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

        // add CRNs
        int counter = 1;
        for(String crn: BotConfiguration.getCRNs()) {
            driver.findElement(By.id("crn_id" + counter)).sendKeys(crn);
        }

        // submit
        driver.findElement(By.className("pagebodydiv")).findElement(By.xpath("//input[@name='REG_BTN' and @value='Submit Changes']")).click();
    }
}
