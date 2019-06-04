import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.Driver;

public class BrowserAutomation
{

    public static String repoName;
    public static String githubUsername;
    public static String githubEmail;
    public  static String githubPassword;
    public static Boolean README_included;

    public static String SSH_address;


    public BrowserAutomation(String repoName, String githubUsername, String githubEmail, String githubPassword, Boolean README_included)
    {
        this.repoName = repoName;
        this.githubUsername = githubUsername;
        this.githubEmail = githubEmail;
        this.githubPassword = githubPassword;
        this.README_included = README_included;

        SSH_address = "git@github.com:" + githubUsername + "/" + repoName + ".git";

    }

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
        githubSignIn(driver);

        //Github: Create a new repository
        githubCreateNewRepo(driver);


        driver.findElement(By.cssSelector("#js-repo-pjax-container > div.container.new-discussion-timeline.experiment-repo-nav >" +
                                                                 "div.repository-content > div.file-navigation.in-mid-page.d-flex.flex-items-start > " +
                                                                 "details.get-repo-select-menu.js-get-repo-select-menu.position-relative.details-overlay." +
                                                                 "details-reset > summary")).click();

        driver.findElement(By.cssSelector("#js-repo-pjax-container > div.container.new-discussion-timeline.experiment-repo-nav > " +
                                                                 "div.repository-content > div.file-navigation.in-mid-page.d-flex.flex-items-start > " +
                                                                 "details.get-repo-select-menu.js-get-repo-select-menu.position-relative.details-overlay.details-reset > " +
                                                                 "div > div > div.get-repo-modal-options > div.clone-options.https-clone-options > form > button")).click();


    }




    public static void githubSignIn(WebDriver d)
    {
        d.findElement(By.cssSelector("#login_field")).sendKeys(githubEmail);
        d.findElement(By.cssSelector("#password")).sendKeys(githubPassword);
        d.findElement(By.cssSelector("#login > form > div.auth-form-body.mt-3 > input.btn.btn-primary.btn-block")).click();
    }


    public static void githubCreateNewRepo(WebDriver d) throws InterruptedException
    {
        d.findElement(By.cssSelector("#repository_name")).sendKeys(repoName);
        if(README_included) {
            d.findElement(By.cssSelector("#repository_auto_init")).click();
        }

        //The time it takes for Github to complete the repo-name availability check depends on internet speed,
        //so this try/catch tries for different internet speeds
        try {
            synchronized (d)
            {
                d.wait(1200);
            }
            d.findElement(By.cssSelector("#new_repository > div.js-with-permission-fields > button")).click();
        }

        catch(Exception e) {
            synchronized (d)
            {
                d.wait(2000);
            }
            d.findElement(By.cssSelector("#new_repository > div.js-with-permission-fields > button")).click();
        }


    }




}
