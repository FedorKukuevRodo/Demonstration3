package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        String ChromeDriverDirectory = System.getProperty("user.dir") + "/webdriver/ChromeDriver/chromedriver";
        System.setProperty("webdriver.chrome.driver", ChromeDriverDirectory);
        WebDriver myDriver = new ChromeDriver();
        myDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        myDriver.get("https://www.rodo.com");
        myDriver.manage().window().maximize();

        String email = "1119test1@testmail.com";
        String password = "374502Qq!";
        System.out.println(registration(myDriver, email, password));
        System.out.println(logout(myDriver));
        System.out.println(login(myDriver, email, password));
        System.out.println(logout(myDriver));

        System.out.println(System.getProperty("user.dir"));
        System.out.println(ChromeDriverDirectory);
    }

    private static String login(WebDriver myDriver, String email, String password) {
        myDriver.get("https://www.rodo.com/login");
        myDriver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        myDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        myDriver.findElement(By.xpath("//button[@type='submit']")).click();
        return "Login test completed";
    }

    private static String registration(WebDriver myDriver, String email, String password) {
        myDriver.get("https://www.rodo.com/sign-up");
        myDriver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Jonathan");
        myDriver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Consumer");
        myDriver.findElement(By.xpath("//input[@id='street']")).sendKeys("Testing street");
        myDriver.findElement(By.xpath("//input[@id='zip']")).sendKeys("11354 ");
        myDriver.findElement(By.xpath("//input[@id='phone']")).sendKeys("7862001435");
        myDriver.findElement(By.cssSelector("#dob")).click();
        myDriver.findElement(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--001']")).click();
        myDriver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        myDriver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        myDriver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys(password);
        myDriver.findElement(By.xpath("//div[@class='checkbox-wrapper']")).click();
        myDriver.findElement(By.xpath("//button[@type='submit']")).click();
        myDriver.findElement(By.xpath("//*[@id='__next']/div/div/div/section/div[3]/div[4]/div/div/div/div[3]/button")).click(); //I agree
        myDriver.findElement(By.xpath("//*[@id='__next']/div/div/div/section/div[3]/div[2]/form/div[3]/button")).click();       //rebates sign up
        myDriver.findElement(By.xpath("//li[3]//div[1]")).click(); //click on coupe image
        return "Registration test completed";
    }

    private static String logout(WebDriver myDriver) {
        myDriver.findElement(By.xpath("//div[@class='toggle-list-button']")).click();
        myDriver.findElement(By.xpath("//span[contains(text(),'Logout')]")).click();
        return "logout test completed";
    }
}
