package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void precondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("User looged out");
        }
    }

    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        logger.info("Login form opened");
        app.getHelperUser().fillLoginForm("test10@test.com", "Codirovka84!");
        logger.info("Login form filled with email test10@test.com & PWD Codirovka84! ");
        app.getHelperUser().submit();
        logger.info("Login form submited");

       Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("User logged in");
//       app.getHelperUser().clickOkButton();
//        Assert.assertTrue(app.getHelperUser().isLogged());
    }
    @Test
    public void loginSuccessUser(){
        User user = new User().withEmail("test10@test.com").withPassword("Codirovka84!");

        app.getHelperUser().openLoginForm();
        logger.info("Login form opened");
        app.getHelperUser().fillLoginForm(user);
        logger.info("Login form filled with email test10@test.com & PWD Codirovka84! ");
        app.getHelperUser().submit();
        logger.info("Login form submited");

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
//       app.getHelperUser().clickOkButton();
//        Assert.assertTrue(app.getHelperUser().isLogged());
    }
        @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test10test.com", "Codirovka84!");
        app.getHelperUser().submit();
//        Assert.assertEquals(app.getHelperUser().getMessage(),('"'+"Login or Password incorrect"+'"'));
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void loginEmptyEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("", "Codirovka84!");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void loginWrongPWD(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test10@test.com", "Cod");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),("\"Login or Password incorrect\""));
    }
//    @Test
//    public void loginEmptyPWD(){
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm("test10@test.com", "");
//        app.getHelperUser().submitLoginForm();
//        Assert.assertEquals(app.getHelperUser().getErrorText(),("Password is required"));
//    }
    @Test
    public void loginUregisteredUSer(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test100@test.com", "Codirovka84!");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),("\"Login or Password incorrect\""));
    }

    @AfterMethod
    public void postcondition(){

        app.getHelperUser().clickOkButton();
    }
}
