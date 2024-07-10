package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm(){
        click(By.xpath("//*[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String pwd) {
        type(By.xpath("//*[@id='email']"), email);
        type(By.xpath("//*[@id='password']"), pwd);
    }
    public void fillLoginForm(User user) {
        type(By.xpath("//*[@id='email']"), user.getEmail());
        type(By.xpath("//*[@id='password']"), user.getPassword());
    }

    public void submit() {
        click(By.xpath("//button[@type='submit']"));
    }


    public void clickOkButton() {
        if(isElementPresent(By.xpath("//button[text()='Ok']"))) {
            click(By.xpath("//button[text()='Ok']"));
        }
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[text()=' Logout ']"));
    }
    public void logout() {
        click(By.xpath("//*[text()=' Logout ']"));
    }

    public String getMessage() {
        pause(3000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }
    public String getErrorText() {
        pause(3000);
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));

        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result =  element.isEnabled();

       return res && !result;
    }
//================Registration================
    public void openRegistrationForm() {
        click(By.xpath("//*[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
//        click(By.id("terms-of-use"));
//        click(By.cssSelector("label[for='terms-of-use']"));
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }
}
