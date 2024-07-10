package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void registrationSuccess(){
        int i = new Random().nextInt(1000)+1000;
        System.out.println(i);
        System.out.println(System.currentTimeMillis());
        int z = (int) ((System.currentTimeMillis()/1000)%3600);
        System.out.println(z);

        User user = new User()
                .withName("Liza")
                .withLastName("Snow")
                .withEmail("snow"+z+"@gmail.com")
                .withPassword("Codirovka84!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
