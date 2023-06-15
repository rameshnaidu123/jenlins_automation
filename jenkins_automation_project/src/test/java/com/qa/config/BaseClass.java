package com.qa.config;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

public class BaseClass {

	public AndroidDriver driver;
	@Parameters({"emulator","platformName","udid","deviceName","automationName"})
	@BeforeClass
	public void beforeTestt(String emulator,String platformName,String udid, String deviceName,String automationName) throws MalformedURLException   {
		// capabilities to create appium session
		UiAutomator2Options options=new UiAutomator2Options();
		options.setCapability("platformName", platformName);
		options.setCapability("deviceName", deviceName);
		options.setCapability("udid", udid);
		URL url = new URL("http://127.0.0.1:4723");
		options.setCapability("automationName", automationName);
		// options.setCapability("appPackage", "com.google.android.youtube");
		// options.setCapability("appActivity","com.google.android.youtube.HomeActivity");
		// options.setCapability("appPackage", "com.arvind.storeapp");
		// options.setCapability("appActivity","com.arvind.storeapp.splash.SplashActivity");
	// 	options.setCapability("app","/home/ramesh.naidu/Documents/AppiumPractice/src/test/resources/app/4.1.4(220)-develop-debug.apk");
		options.setCapability("unlockType","pin");
		options.setCapability("unlockKey","9966");
		options.setCapability("autoGrantPermissions",true);
		//        if(emulator.equalsIgnoreCase("true"))
		//        {
		//  options.setCapability("avd", deviceName);
		//  options.setCapability("avdLaunchTimeout", 180000);
		// creating driver session
		driver=new AndroidDriver(url,options);
		// }
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	public AppiumDriver getDriver(){
		return driver;
	}
	public void waitForVisibility(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	public void waitForClickable(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}
	public void sendKeys(WebElement e, String txt)
	{
		waitForVisibility(e);
		e.sendKeys(txt);
	}
	public String getAttribute(WebElement e, String attribute)
	{
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}
	public String getText(WebElement e)
	{
		String txt = null;
		txt = getAttribute(e, "text");
		return txt;
	}
	public void click(WebElement e, String txt)
	{
		waitForClickable(e);
		e.click();
	}
	public void scroll(int left,int top,int width, int height ,String direction){
		boolean canScrollMore;
		for(int i=0;i<1;i++){
			driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
					"left", left, "top", top, "width", width, "height", height,
					"direction", direction,
					"percent", 0.8
					));
		}
	}
	public void clickCoordinate(){
		driver.executeScript("mobile: clickGesture", ImmutableMap.of(
				"x",58,
				"y",119
				));
	}
	public void waitForActivity(String desiredActivity) throws InterruptedException
	{
		int counter = 0;
		do {
			Thread.sleep(1000);
			counter++;
		} while(driver.currentActivity().contains(desiredActivity) && (counter<=5));
		// log("Activity appeared :" + driver.currentActivity(), true);
	}

}
