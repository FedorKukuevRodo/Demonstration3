package com.company;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //find chrome driver on your machine, only one of them should be uncommented
        //FOR MAC:
        System.setProperty("webdriver.chrome.driver", "/Users/jillduhl/Desktop/webdriver/ChromeDriver/chromedriver");
        //FOR WINDOWS:
        //System.setProperty("webdriver.chrome.driver", "D:\\webDriver\\webdriver\\ChromeDriver\\chromedriver.exe");

        WebDriver myDriver = new ChromeDriver();
        myDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //if it can't find an element on the
        // page right away it does not stop the script immediately, it keeps trying for 10sec
        myDriver.get("https://www.rodo.com");   //open rodo.com in chrome browser
        myDriver.manage().window().maximize();   //open browser to fullscreen
        String email = "seltest3+1122@testmail.com"; //email that's going to be used for registration and login
        String password = "374502Qq!";           //password
        //Here the script starts to work
//        registration(myDriver, email, password);                    //calling registration function
//        logout(myDriver);                                           //calling logout function
        login(myDriver, email, password);                           //calling login function
        freeSearchAZ(myDriver);                                     //calling freeSearch function
        logout(myDriver);                                           //calling logout function
        myDriver.close();                                           //close the browser
    }
    private static void login(WebDriver myDriver, String email, String password) {
        myDriver.get("https://www.rodo.com/login");                                     //open rodo.com/login
        myDriver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);       //type email in the input field
        myDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(password); //type password
        myDriver.findElement(By.xpath("//button[@type='submit']")).click();             //click login
        System.out.println("login test completed");                                                  //write in console: test completed
    }
    private static void registration(WebDriver myDriver, String email, String password) {
        myDriver.get("https://www.rodo.com/sign-up");                                                   //open rodo.com/signup
        myDriver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Jonathan");   //enter Jonathan in the input field
        myDriver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Consumer");    //enter Consumer
        myDriver.findElement(By.xpath("//input[@id='street']")).sendKeys("Testing street");//enter street address
        myDriver.findElement(By.xpath("//input[@id='zip']")).sendKeys("11354 ");           //enter zip
        myDriver.findElement(By.xpath("//input[@id='phone']")).sendKeys("7862001435");     //inter phone number
        myDriver.findElement(By.cssSelector("#dob")).click();                                           //click on DOB input field
        myDriver.findElement(By.xpath("//*[@id='__next']/div/div/div/section/div[3]/form/div[4]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div[2]/div[1]")).click(); // click on 6th of January
        myDriver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);                         //enter email
        myDriver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);                   //enter password
        myDriver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys(password);            //enter confirm password
        myDriver.findElement(By.xpath("//div[@class='checkbox-wrapper']")).click();                     //click on 'i agree' checkbox
        myDriver.findElement(By.xpath("//button[@type='submit']")).click();                             //click on sign-up button
        myDriver.findElement(By.xpath("//*[@id='__next']/div/div/div/section/div[3]/div[4]/div/div/div/div[3]/button")).click(); //I agree to soft credit check
        myDriver.findElement(By.xpath("//*[@id='__next']/div/div/div/section/div[3]/div[2]/form/div[3]/button")).click();       //click on sign-ip button on rebates page
        System.out.println("Registration test completed");                                              //write in console: test completed
    }
    private static void logout(WebDriver myDriver) {
        myDriver.findElement(By.xpath("//div[@class='toggle-list-button']")).click(); //click on top-right menu
        myDriver.findElement(By.xpath("//span[contains(text(),'Logout')]")).click();  //click on logout button
        System.out.println("logout test completed");                                  //write in console: test completed
    }

    private static void freeSearchAZ(WebDriver myDriver) {
        String[] makeList = {"Honda", "BMW", "Kia", "Nissan", "Toyota", "Ford", "Mazda"}; //list of makes
        String searchResults;                                                        //how many cars of this make found
        for (String make: makeList)                 //starting the cycle
        {
            myDriver.findElement(By.xpath("//input[@id='search_term']")).sendKeys(make); //typing make (honda/bmw/kia..etc) in the search input field
            myDriver.findElement(By.xpath("//button[@class='search-btn']")).click(); //clicking on the search icon
            searchResults = myDriver.findElement(By.xpath("//*[@id='__next']/div/div/div/div/div[2]/div[1]/strong[3]")).getText();  //getting the number of search results
            System.out.println(make + ": " + searchResults + " results");   //show the number of search results in console
            myDriver.get("https://www.rodo.com");           //back to home page
        }                                                   //jump in the beginning of cycle with the next make
        System.out.println("freeSearchAZ test completed");  //write in console: test completed
    }
}