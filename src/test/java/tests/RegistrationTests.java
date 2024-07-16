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
    public void registrationSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        System.out.println(System.currentTimeMillis());
        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
        System.out.println(z);

        User user = new User()
                .withName("Liza")
                .withLastName("Snow")
                .withEmail("snow" + z + "@gmail.com")
                .withPassword("Codirovka84!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }

        @Test
        public void registrationEmptyName() {
            int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
            User user = new User()
                    .withName("")
                    .withLastName("Snow")
                    .withEmail("snow" + z + "@gmail.com")
                    .withPassword("Codirovka84!");
            app.getHelperUser().openRegistrationForm();
            app.getHelperUser().fillRegistrationForm(user);
            app.getHelperUser().checkPolicyXY();
            app.getHelperUser().submit();
            Assert.assertEquals(app.getHelperUser().getErrorText(), "Name is required");
            Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        }
    @Test
    public void registrationEmptyLastname() {
        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Liza")
                .withLastName("")
                .withEmail("snow" + z + "@gmail.com")
                .withPassword("Codirovka84!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationWrongEmail() {
             User user = new User()
                .withName("Liza")
                .withLastName("Snow")
                .withEmail("snowgmail.com")
                .withPassword("Codirovka84!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Wrong email format\n" +
                "Wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationEmptyEmail() {
        User user = new User()
                .withName("Liza")
                .withLastName("Snow")
                .withEmail("")
                .withPassword("Codirovka84!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationEmptyPasword() {
        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Liza")
                .withLastName("Snow")
                .withEmail("snow" + z + "@gmail.com")
                .withPassword("");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationWrongPasword() {
        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Liza")
                .withLastName("Snow")
                .withEmail("snow" + z + "@gmail.com")
                .withPassword("Cod123");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationExistedUser() {
        User user = new User()
                .withName("Liza")
                .withLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("Codirovka84!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"User already exists\"");
    }
    @Test
    public void RegistrationWithoutCheckBox(){
        int i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        System.out.println(System.currentTimeMillis());
        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
        System.out.println(z);

        User user = new User()
                .withName("Liza")
                .withLastName("Snow")
                .withEmail("snow" + z + "@gmail.com")
                .withPassword("Codirovka84!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
//        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
