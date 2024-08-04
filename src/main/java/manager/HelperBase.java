package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import com.google.common.io.Files;

import java.io.IOException;
import java.util.List;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if(text!=null) {
            element.sendKeys(text);
        }
    }
    public void clearNew(WebElement element){
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);
    }
    public void clearTextField(By locator){
        WebElement element = wd.findElement(locator);
        String os = System.getProperty("os.name");
        if(os.startsWith("Win")){
            element.sendKeys(Keys.CONTROL, "a");
        }else{
            element.sendKeys(Keys.COMMAND, "a");
        }
        element.sendKeys(Keys.DELETE);

    }
    public void click(By locator){
        wd.findElement(locator).click();
    }

    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
       return list.size()>0;
    }

    public boolean isAlertPresent(String message){
        Alert  alert = new WebDriverWait(wd,5).until(ExpectedConditions.alertIsPresent());
        if(alert!=null &&  alert.getText().contains(message)){
            pause(3000);
            alert.accept();
            return true;
        }
        return false;
    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void submit() {
        click(By.xpath("//button[@type='submit']"));
    }
    public String  getMessage() {
        pause(3000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(tmp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));

        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result =  element.isEnabled();

        return res && !result;
    }
    public String getErrorText() {
        pause(3000);
        return wd.findElement(By.cssSelector("div.error")).getText();
    }
}
