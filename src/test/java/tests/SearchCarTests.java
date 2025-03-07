package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        app.getHelperCar().navigateByLogo();
    }
    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Rehovot", "8/10/2024", "8/31/2024");
       app.getHelperCar().getScreen("src/test/Screenchots/current.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }
    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Rehovot", "10/15/2024", "12/10/2024");
        app.getHelperCar().getScreen("src/test/Screenchots/currentYear.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess(){
        app.getHelperCar().searchAnyPeriod("Rehovot", "9/26/2024", "3/8/2025");
        app.getHelperCar().getScreen("src/test/Screenchots/anyPeriod.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }
    @Test
    public void negativeSearch(){
        app.getHelperCar().searchNotValidPeriod("Rehovot", "6/26/2024", "9/8/2024");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperCar().getErrorText(), " You can't pick date before today ");
        // You can't pick date after one year
    }
}
