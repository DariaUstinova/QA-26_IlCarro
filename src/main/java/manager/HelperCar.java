package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{

    public HelperCar(WebDriver wd) {
        super(wd);
    }


    public void openCarForm() {
        pause(500);
        click(By.xpath("//*[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        type(By.id("price"), car.getPrice()+"");//concotinatsyia i prevrachaet v String
        type(By.id("about"), car.getAbout());
    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHomePage() {
        click((By.xpath("//button[text()='Search cars']")));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextField(By.id("dates"));
        click(By.id("dates"));

        String[] from = dateFrom.split("/"); //["7"]["27"]["2024"]
        String locatorFrom = "//div[text()=' "+from[1]+ " ']";
        click(By.xpath(locatorFrom));

        String[] to = dateTo.split("/");
        click(By.xpath("//div[text()=' "+to[1]+ " ']"));

    }

    private void typeCity(String city) {
        clearTextField(By.id("city"));
        type(By.id("city"),city);
        click(By.cssSelector("div.pac-item"));
        pause(300);
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dataFrom, String dataTo) {
        //"10/15/2024", "12/10/2024"
        typeCity(city);
        clearTextField(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now(); //returns today's date
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy")); //2024-10-15
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("M/d/yyyy")); //2024-10-12
        // Ex: LocalDate from1 = LocalDate.parse("2013:23/05", DateTimeFormatter.ofPattern("yyyy:dd/MM"));
        int diffMonth = from.getMonthValue()-month;
        if(diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()=' "+from.getDayOfMonth()+ " ']"));
        diffMonth = to.getMonthValue()-from.getMonthValue();
        if(diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }
        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());  // ("//div[text()=' "+to[1]+ " ']")
        click(By.xpath(locator));
    }

    private void clickNextMonthBtn(int diffMonth) {
        for(int i=0; i<diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }

    public void searchAnyPeriod(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearTextField(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to= LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear;
        int diffMonth;
        diffYear = from.getYear()-now.getYear();
        if(diffYear==0){
            diffMonth=from.getMonthValue()-now.getMonthValue();
        }else{
            diffMonth=12 - now.getMonthValue()+from.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);

        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

        diffYear = to.getYear()- from.getYear();
        if(diffYear==0){
            diffMonth=to.getMonthValue()-from.getMonthValue();
        }else{
            diffMonth=12- from.getMonthValue()+to.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void searchNotValidPeriod(String city, String dataFrom, String dataTo) {
        typeCity(city);
        pause(300);
        clearTextField(By.id("dates"));
        type(By.id("dates"), dataFrom+" - "+dataTo);
        click(By.cssSelector("div.cdk-overlay-backdrop"));
    }
}
