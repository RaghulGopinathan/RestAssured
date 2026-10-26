package com.framework.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.framework.config.Config;

public class BaseTest {

    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = Config.BASE_URL;
    }

    public void log (String message){
        System.out.println(message);
    }

    @AfterMethod
    public void teardown(){
        RestAssured.baseURI = null;
    }



}
