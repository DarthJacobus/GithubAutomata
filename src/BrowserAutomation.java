import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserAutomation
{


    public static void main(String args[]) throws InterruptedException
    {


        //Web driver setup
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jacob\\Downloads\\chromedriver_74.exe");

        //Chrome driver setup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);

        //Navigate to Github: Create new repository
        driver.navigate().to("https://github.com/new");

        //Github: sign-in screen
        driver.findElement(By.cssSelector("#login_field")).sendKeys("jacobxwestin@gmail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("Lucatoni77!");
        driver.findElement(By.cssSelector("#login > form > div.auth-form-body.mt-3 > input.btn.btn-primary.btn-block")).click();

        //Github: Create a new repository
        driver.findElement(By.cssSelector("#repository_name")).sendKeys("GithubAutomata");
        driver.findElement(By.cssSelector("#repository_auto_init")).click();

        //The time it takes for Github to complete the repo-name availability check depends on internet speed,
        //so this try/catch tries for different internet speeds
        try {
            synchronized (driver)
            {
                driver.wait(1200);
            }
            driver.findElement(By.cssSelector("#new_repository > div.js-with-permission-fields > button")).click();
        }

        catch(Exception e) {
            synchronized (driver)
            {
                driver.wait(2000);
            }
            driver.findElement(By.cssSelector("#new_repository > div.js-with-permission-fields > button")).click();
        }


        driver.findElement(By.cssSelector("#js-repo-pjax-container > div.container.new-discussion-timeline.experiment-repo-nav >" +
                                                                 "div.repository-content > div.file-navigation.in-mid-page.d-flex.flex-items-start > " +
                                                                 "details.get-repo-select-menu.js-get-repo-select-menu.position-relative.details-overlay." +
                                                                 "details-reset > summary")).click();

        driver.findElement(By.cssSelector("#js-repo-pjax-container > div.container.new-discussion-timeline.experiment-repo-nav > " +
                                                                 "div.repository-content > div.file-navigation.in-mid-page.d-flex.flex-items-start > " +
                                                                 "details.get-repo-select-menu.js-get-repo-select-menu.position-relative.details-overlay.details-reset > " +
                                                                 "div > div > div.get-repo-modal-options > div.clone-options.https-clone-options > form > button")).click();

        driver.findElement(By.cssSelector("#js-repo-pjax-container > div.container.new-discussion-timeline.experiment-repo-nav > div.repository-content > " +
                                                                 "div.file-navigation.in-mid-page.d-flex.flex-items-start > details.get-repo-select-menu.js-get-repo-select-menu.position-" +
                                                                 "relative.details-overlay.details-reset > div > div > div.get-repo-modal-options > div.clone-options.https-clone-options > div > " +
                                                                 "div > clipboard-copy > svg > path")).click();










    }




}
