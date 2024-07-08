package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void submitLoginForm() {
        click(By.xpath("//button[@type='submit']"));
    }



    public String getMessage() {
        pause(3000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }

    public void clickOkButton() {
        click(By.xpath("//button[text()='Ok']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[text()=' Logout ']"));
    }
    public void logout() {
        click(By.xpath("//*[text()=' Logout ']"));
    }
}
