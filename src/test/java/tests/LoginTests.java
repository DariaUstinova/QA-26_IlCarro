package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void precondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginPositive(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test10@test.com", "Codirovka84!");
        app.getHelperUser().submitLoginForm();

       Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
//       app.getHelperUser().clickOkButton();
//        Assert.assertTrue(app.getHelperUser().isLogged());
    }
        @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test10@testcom", "Codirovka84!");
        app.getHelperUser().submitLoginForm();
        Assert.assertEquals(app.getHelperUser().getMessage(),('"'+"Login or Password incorrect"+'"'));
    }
    @Test
    public void loginWrongPWD(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test10@test.com", "Cod");
        app.getHelperUser().submitLoginForm();
        Assert.assertEquals(app.getHelperUser().getMessage(),('"'+"Login or Password incorrect"+'"'));
    }
    @Test
    public void loginUregisteredUSer(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test100@test.com", "Codirovka84!");
        app.getHelperUser().submitLoginForm();
        Assert.assertEquals(app.getHelperUser().getMessage(),('"'+"Login or Password incorrect"+'"'));
    }

    @AfterMethod
    public void postcondition(){
        app.getHelperUser().clickOkButton();
    }
}
