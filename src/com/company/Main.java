package com.company;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.mail.MessagingException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    private static String rodoUrl;
    private static String Env;

    public static void main(String[] args) throws IOException, InterruptedException, MessagingException {
        //find chrome driver on your machine, only one of them should be uncommented
        //FOR MAC:
        System.setProperty("webdriver.chrome.driver", "/Users/jillduhl/Desktop/webdriver/ChromeDriver/chromedriver");
        //FOR WINDOWS:
        //System.setProperty("webdriver.chrome.driver", "D:\\webDriver\\webdriver\\ChromeDriver\\chromedriver.exe");
        //Testing environment, only one of them should be uncommented
        Env = "prod"; // Testing on prod
        //Env = "dev"; // Testing on dev

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //if it can't find an element on the
        // page right away it does not stop the script immediately, it keeps trying for 10sec
        if (Env == "prod") {
            rodoUrl = "https://www.rodo.com";
        } else {rodoUrl = "https://web.dev.rodo.com";}
        driver.get(rodoUrl);   //open rodo.com in chrome browser
        driver.manage().window().maximize();   //open browser to fullscreen
        String email = "fkukuev@honcker.com";   //email that's going to be used for registration and login
        //String email = "jduhl+test" + (int)(Math.random() * 99999 + 1) + "@test.com"; //in case if you want email to be random
        String password = "374502Qq!";                        //password

        //Here the script starts to work
        //registration(driver, email, password);                      //calling registration function
        //logout(driver);                                           //calling logout function
        login(driver, email, password);                           //calling login function
        freeFullSearch(driver);
        //freeMakeSearchAZ(driver);                                     //calling freeSearch function
        //searchSVPcomparePrice(driver);                            //calling searchSVPcomparePrice function
        //logout(driver);                                           //calling logout function
        //gmail(driver, "fedor.kukuev@rodo.com", "lul", "testbody new updated testbody new updatedtestbody new updated ");
        driver.close();                                           //close the browser
    }
    private static void login(WebDriver driver, String email, String password) {
        driver.get(rodoUrl + "/login");                                               //open rodo.com/login
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);       //type email in the input field
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password); //type password
        driver.findElement(By.xpath("//button[@type='submit']")).click();             //click login
        System.out.println("login test completed");                                     //write in console: test completed
    }

    private static void registration(WebDriver driver, String email, String password) {
        String firstName = Env == "prod" ? "Jonathan" : "Lance";                                      //first name: Jonathan for prod, Lance for dev
        String lastName = Env == "prod" ? "Consumer" : "Syed";                                        //last name: Consumer for prod, Syed for dev
        driver.get(rodoUrl + "/sign-up");                                                           //open rodo.com/signup
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstName);  //enter first name in the input field
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastName);    //enter last name in the input field
        driver.findElement(By.xpath("//input[@id='street']")).sendKeys("1087 CRAIGVILLE RD");//enter street address
        driver.findElement(By.xpath("//input[@id='zip']")).sendKeys("10918 ");           //enter zip
        driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("7862001435");     //inter phone number
        driver.findElement(By.cssSelector("#dob")).click();                                           //click on DOB input field
        driver.findElement(By.cssSelector("#dob")).sendKeys(Keys.ENTER);                              // click ENTER
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);                         //enter email
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);                   //enter password
        driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys(password);            //enter confirm password
        driver.findElement(By.xpath("//div[@class='checkbox-wrapper']")).click();                     //click on 'i agree' checkbox
        driver.findElement(By.xpath("//button[@type='submit']")).click();                             //click on sign-up button
        driver.findElement(By.xpath("//*[@id='__next']/div/div/div/section/div[3]/div[4]/div/div/div/div[3]/button")).click(); //I agree to soft credit check
        driver.findElement(By.xpath("//*[@id='__next']/div/div/div/section/div[3]/div[2]/form/div[3]/button")).click();       //click on sign-ip button on rebates page
        System.out.println("Registration test completed");                                              //write in console: test completed
    }
    private static void logout(WebDriver driver) {
        driver.findElement(By.xpath("//div[@class='toggle-list-button']")).click(); //click on top-right menu
        driver.findElement(By.xpath("//span[contains(text(),'Logout')]")).click();  //click on logout button
        System.out.println("logout test completed");                                  //write in console: test completed
    }

    private static void freeMakeSearchAZ(WebDriver driver) {
        String[] makeList = {
                "Acura", "ALFA ROMEO", "Audi", "BMW", "Buick", "Cadillac", "Chevrolet",
                //"Chrysler", "Dodge", "Fiat", "Ford", "Genesis", "GMC", "Honda",
                //"Hyundai", "Infiniti", "Jaguar", "Jeep", "Land Rover", "Lexus", "Mazda",
                //"Mercedes", "Mini", "Mitsubishi", "Porsche", "RAM", "Subaru", "Toyota",
                //"Volkswagen", "Volvo",
                "Maserati"};            //list of makes
        System.out.println(makeList.toString());
        makeList[2] = "audiUpdated";
        System.out.println(makeList.toString());
        String searchResults;                                                        //how many cars of this make found
        for (String make: makeList)                 //starting the cycle
        {
            driver.findElement(By.xpath("//input[@id='search_term']")).sendKeys(make); //typing make (honda/bmw/kia..etc) in the search input field
            driver.findElement(By.xpath("//button[@class='search-btn']")).click(); //clicking on the search icon
            searchResults = driver.findElement(By.xpath("//*[@id='__next']/div/div/div/div/div[2]/div[1]/strong[3]")).getText();  //getting the number of search results
            System.out.println(make + ": " + searchResults + " results");   //show the number of search results in console
            driver.get(rodoUrl);                          //back to home page
        }                                                   //jump in the beginning of cycle with the next make
        System.out.println("freeSearchAZ test completed");  //write in console: test completed
    }

    private static void searchSVPcomparePrice (WebDriver driver) {
        String[] makeList = {
                "Acura", "ALFA ROMEO", "Audi", "BMW", "Buick", "Cadillac", "Chevrolet",
                //"Chrysler", "Dodge", "Fiat", "Ford", "Genesis", "GMC", "Honda",
                //"Hyundai", "Infiniti", "Jaguar", "Jeep", "Land Rover", "Lexus", "Mazda",
                //"Mercedes", "Mini", "Mitsubishi", "Porsche", "RAM", "Subaru", "Toyota",
                //"Volkswagen", "Volvo",
                 "Maserati"};            //list of makes

        String searchPrice;                                     //Variable to store the price on the search page
        String SVPprice;                                        //Variable to store the price on the SVP
        for (String make: makeList)                             //starting the cycle
        {
            driver.findElement(By.xpath("//input[@id='search_term']")).sendKeys(make); //typing honda/bmw/kia..etc in the search input field
            driver.findElement(By.xpath("//button[@class='search-btn']")).click();     //clicking on the search icon
            searchPrice = driver.findElement(By.xpath("//*[@id='__next']/div/div/div/div/ul/li[2]/div/div[1]/div[2]/strong")).getText(); // getting the price of the first car on the page
            if (searchPrice.equals("Pricing Unavailable")) {        //if that price is unavailable
                System.out.println(make + ' ' + searchPrice);       //write in console: [make] is pricing unavailable
                driver.get(rodoUrl);                              //go back to home page
                continue;                                           // jumping to the next make
            }
            System.out.println(make + " - price on search page: " +searchPrice);                   //if price is ok: write in console the price
            driver.findElement(By.xpath("//*[@id='__next']/div/div/div/div/ul/li[2]")).click(); //click on the tile to go to SVP
            SVPprice = driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div/div[5]/div[2]/div[1]/div[1]/div/span")).getText();  //on SVP get the price
                                                      //*[@id="__next"]/div/div[2]/div/div/div[5]/div[2]/div[1]/div[1]/div/span
            System.out.println(make + "- price on SVP: " + SVPprice);  //write the SVP price in console
            if (searchPrice.equals(SVPprice)) {                        //if price is the same on both pages
                System.out.println(make + " passed");                  //write in console that make passed
            } else  System.out.println(make + " failed");              //if price is not the same: write in console that make failed
            driver.get(rodoUrl);                                     //go back to home page
        }                                                              //end of cycle
        System.out.println("searchSVPcomparePrice test completed");    //write in console: test completed
    }

    private static void freeFullSearch (WebDriver driver) throws IOException, InterruptedException, MessagingException {

        List<List<String>> records = new ArrayList<>();                     //2D array to store cars from csv, every row is array with cells
        try (BufferedReader br = new BufferedReader(new                     //reading from csv
                FileReader("D:\\likeSSS\\Demonstration3-master\\testData\\All_Makes_Model_Trim_Year.csv"))) {
            String line;
            String headerLine = br.readLine();                              //consumer the first line in CSV file ("year,make, model, trim")
            while ((line = br.readLine()) != null) {                        //cycle to go through the csv until the end
                String[] values = line.split(",");
                records.add(Arrays.asList(values));                         //filling the array
            }
        }
        ArrayList<String> pricingUnavailableCars = new ArrayList<String>(); //array for cars that return Pricing Unavailable
        List<List<String>> pricingUnavailableCarsReport = new ArrayList<>();
        ArrayList<String> notFoundCars = new ArrayList<String>();           //array for cars that return 0 results
        List<List<String>> notFoundCarsReport = new ArrayList<>();
        ArrayList<String> cantLoadPricingCars = new ArrayList<String>();    //array for cars that can't load the price
        List<List<String>> cantLoadPricingCarsReport = new ArrayList<>();
        String carPrice;                                                 //variable to store price of the first car tile after getting the results
        boolean switchedTo2020 = false;                                 //flag to see if switched to 2020 cars in the list already or not
        driver.findElement(By.xpath("//input[@id='search_term']")).sendKeys("test"); // :( shady way to wait until login finished before calling another "driver.get"
        driver.get(rodoUrl + "/search-results");                          //open search by make and model page
        driver.findElement(By.xpath("//span[@class='selected-list-item']")).click();    //click on dropdown
        driver.findElement(By.xpath("//span[@data-value='2019']")).click();             //choose 2019

        for (List<String> record : records) {                               //starting cycle through all the cars from csv
            String car = record.toString();                                 //string variable  containing make model and trim
            int carYear = Integer.parseInt(car.substring(1,5));
            car = car.replace(",", "");                  //deleting commas
            car = car.substring(6, car.length() - 1 );                      //deleting square brackets: "[" and "]" symbols and year
            if (carYear == 2020 && !switchedTo2020) {                       //if it's the first 2020 car
                driver.findElement(By.xpath("//span[@class='selected-list-item']")).click();    //click on dropdown
                driver.findElement(By.xpath("//span[@data-value='2020']")).click();             //choose 2020
                switchedTo2020 = true;                                      //dropdown to 2020 has been changed to 2020
            }
            driver.findElement(By.xpath("//*[@id='search_term']")).sendKeys(car); //now we type this in input field
            driver.findElement(By.xpath("//button[@class='search-btn']")).click();//click search button

            List resultsList = driver.findElements(By.xpath("//*[@id='__next']/div/div/div/div/ul/li[2]/div/div[1]/div[2]/strong")); //trying to find at least one price on the page
            if (resultsList.size() > 0) {                                                                                                //if you can find it
                carPrice = driver.findElement(By.xpath("//*[@id='__next']/div/div/div/div/ul/li[2]/div/div[1]/div[2]/strong")).getText();  //getting the price of the first car tile
                System.out.println(car + " price: " + carPrice);   //show the price in console
                if (carPrice.equals("Pricing Unavailable")) {        //if that price is unavailable
                    pricingUnavailableCars.add(car);                 //adding this car to the array with pricing unavailable cars
                    pricingUnavailableCarsReport.add(record);
                }
            } else if (driver.findElement(By.cssSelector("div[class*='SearchResultsError']")).isDisplayed()){ //if price is not found and you see Sorry page
                System.out.println(car + " not found");    //write in console
                notFoundCars.add(car);                     //add the car to the array with notFoundCars
                notFoundCarsReport.add(record);
            } else {
                System.out.println(car + " can't load the price");
                cantLoadPricingCars.add(car);
                cantLoadPricingCarsReport.add(record);
            }
            driver.findElement(By.xpath("//*[@id='search_term']")).clear(); //clear the input field
        }                                                   //jump in the beginning of cycle with the next car
        System.out.println("main cycle completed");
        System.out.println("pricingUnavailableCars: " + pricingUnavailableCars); //write in console the array with pricing unavailable cars
        System.out.println("pricingUnavailableCars: " + pricingUnavailableCarsReport);
        System.out.println("notFoundCars: " + notFoundCars);                //write in console the array with not Found cars
        System.out.println("notFoundCars: " + notFoundCarsReport);
        System.out.println("cantLoadPricingCars: " + cantLoadPricingCars); //write in console the array with car that could not load the price/page
        System.out.println("cantLoadPricingCars: " + cantLoadPricingCarsReport);
//!        if (cantLoadPricingCars.size() > 0) {                           //if there any cars that could not load the price
//            Iterator itr = cantLoadPricingCars.iterator();              //iterator to through the array with  cars
//            while (itr.hasNext())                                       //starting the cycle to go through them again
//            {
//                String cantLoadPricingCar = (String)itr.next();
//                driver.findElement(By.xpath("//*[@id='search_term']")).sendKeys(cantLoadPricingCar); //searching for the car for 2nd time
//                driver.findElement(By.xpath("//button[@class='search-btn']")).click();        //click search button
//                List resultsList = driver.findElements(By.xpath("//*[@id='__next']/div/div/div/div/ul/li[2]/div/div[1]/div[2]/strong"));//trying to find at least one price on the page
//                if (resultsList.size() > 0) {                                                                                             //if you can find it
//                    carPrice = driver.findElement(By.xpath("//*[@id='__next']/div/div/div/div/ul/li[2]/div/div[1]/div[2]/strong")).getText();  //getting the price of the first car tile
//                    System.out.println(cantLoadPricingCar + " price: " + carPrice);   //show the price in console
//                    if (carPrice.equals("Pricing Unavailable")) {              //but still the price can be "pricing unavailable" so we check that
//                        pricingUnavailableCars.add(cantLoadPricingCar);        //adding this car to the array with pricing unavailable cars
//                    }
//                    itr.remove();                                              //remove this car from the array because we managed to get the price/pricingUnavailable
//                } else if (driver.findElement(By.cssSelector("div[class*='SearchResultsError']")).isDisplayed()) { //if price is not found and you see Sorry page
//                    System.out.println(cantLoadPricingCar + " not found");    //write in console
//                    notFoundCars.add(cantLoadPricingCar);                     //add the car to the with notFoundCars
//                    itr.remove();                                             //remove this car from the array because it actually belongs to "not found" category
//                } else {                                                     //if price again cannot be found
//                    System.out.println(cantLoadPricingCar + " can't load the price again");       //write in console
//                }
//            }
//            System.out.println("additional cycle completed");
//            System.out.println("notFoundCars: " + notFoundCars); //write in console the array with not Found cars
//            System.out.println("pricingUnavailableCars: " + pricingUnavailableCars); //write in console the array with pricing unavailable cars
//            System.out.println("cantLoadPricingCars: " + cantLoadPricingCars);       //write in console the array with car that could not load the price/page
//!        }

        // Writing to cvc files
        writeToCSV(pricingUnavailableCarsReport, "testData/pricUnvReport.csv");
        writeToCSV(notFoundCarsReport, "testData/notFoundReport.csv");
        writeToCSV(cantLoadPricingCarsReport, "testData/cantLoadReport.csv");

        //sending an email with report
        String to[] = {"fedor.kukuev@rodo.com"};
        SendMail.send("rodotestmail@gmail.com", to, "sendMail subject", "Check the PDF attachment.");

        String emailReport = "Pricing Unavailable Cars: " + pricingUnavailableCars.toString() + System.lineSeparator()
                + "Not Found Cars: " + notFoundCars.toString() + System.lineSeparator()
                + "Can't Load Pricing Cars: " + cantLoadPricingCars.toString();
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        gmail(driver, "fedor.kukuev@rodo.com", "Free Full Search Report: " + formatter.format(date), emailReport);
        driver.get(rodoUrl);                          //back to home page
        System.out.println("freeFullSearch test completed");  //write in console: test completed
    }

    private static void writeToCSV(List<List<String>> dataArray, String path) throws IOException {
        if (dataArray.size() > 0) {
            FileWriter csvWriter = new FileWriter(path);
            csvWriter.append("yearLUL");
            csvWriter.append(",");
            csvWriter.append("make");
            csvWriter.append(",");
            csvWriter.append("model");
            csvWriter.append(",");
            csvWriter.append("trim");
            csvWriter.append("\n");

            for (List<String> rowData : dataArray) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        }
    }

    private static void gmail(WebDriver driver, String recipient, String subject, String body) throws InterruptedException {
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
        driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys("rodotestmail@gmail.com");
        driver.findElement(By.xpath("//*[@id='identifierNext']")).click();
        driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")).sendKeys("374502Qq!");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id='passwordNext']"))).click().perform();
        driver.findElement(By.xpath("//*[contains(text(), 'Compose')]")).click();
        driver.findElement(By.xpath("//*[@name='to']")).sendKeys(recipient);
        driver.findElement(By.xpath("//*[@name='subjectbox']")).sendKeys(subject);
        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(body);
        Thread.sleep(3000);                                                                         //very shady way to avoid alert error on gmail
        driver.findElement(By.xpath("(//*[contains(text(), 'Send')])[2]")).click(); //
        System.out.println("gmail test completed");
    }
}


