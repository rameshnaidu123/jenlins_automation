package com.qa.tests;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.qa.config.BaseClass;

public class SampleTest extends BaseClass {
  @Test
  public void f() throws InterruptedException {
	  
	  
	  driver.executeScript("mobile:swipeGesture",ImmutableMap.of(
			  
              "left",350,
              "top",509,
              "width",360,
              "height",1050,
              "direction","down",
              "percent",0.80

      ));

Thread.sleep(2000);

driver.executeScript("mobile:swipeGesture",ImmutableMap.of(

"left",350,
"top",509,
"width",360,
"height",1050,
"direction","up",
"percent",0.80

));
  }
}
