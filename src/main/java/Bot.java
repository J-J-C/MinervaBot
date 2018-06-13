import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Bot {
    // abstract the variable for future development
    static {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver-win.exe");
        System.setProperty("webdriver.chrome.driver", "chromedriver");
    }

    static String MinervaRoot = "https://horizon.mcgill.ca/pban1/twbkwbis.P_WWWLogin";
    static String McGillEmail = "";
    static String McGillPassword = "";
    static String CRN1 = "";
    static String CRN2 = "";

    static int fullInterval = 5000;
    static int smallInterval = 5000;

    static int bigLimit = 100;
    static int smallLimit = 5;
    // initialization the driver


    static WebDriver driver = new ChromeDriver();

    public static void main(String[] args) {
        int attempt = 0;
        // final registration, fuck the system
        while(attempt < bigLimit) {
            setup();
            int miniAttempt = 0;
            while(miniAttempt < smallLimit){
                register();
                wait(smallInterval);
                miniAttempt++;
            }
            // logout
            driver.findElement(By.linkText("EXIT")).click();
            wait(fullInterval);
            attempt++;
            System.out.println("attempt: " + attempt);
        }
        driver.close();
        // exit the program explicitly
        System.exit(0);
    }

    private static void wait(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void setup() {
        // login to minerva
        driver.get(MinervaRoot);
        driver.findElement(By.id("mcg_un")).sendKeys(McGillEmail);
        driver.findElement(By.id("mcg_pw")).sendKeys(McGillPassword);
        driver.findElement(By.id("mcg_un_submit")).click();

        // student menu -> registration menu -> quick add drop
        driver.findElement(By.linkText("Student Menu")).click();
        driver.findElement(By.linkText("Registration Menu")).click();
        driver.findElement(By.linkText("Quick Add or Drop Course Sections")).click();

        // select the term
        Select term = new Select(driver.findElement(By.id("term_id")));
        term.selectByValue("201705");
        driver.findElement(By.className("pagebodydiv")).findElement(By.tagName("input")).click();
    }

    private static void register() {
        driver.findElement(By.id("crn_id1")).sendKeys(CRN1);
        driver.findElement(By.id("crn_id2")).sendKeys(CRN2);
        driver.findElement(By.className("pagebodydiv")).findElement(By.xpath("//input[@name='REG_BTN' and @value='Submit Changes']")).click();
    }
}
