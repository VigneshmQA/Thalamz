package com.qa.base;

import com.microsoft.playwright.Page;
import com.qa.factory.PlaywrightFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected PlaywrightFactory pf;
    protected Page page;

    @BeforeMethod
    public void setup() {
        pf = new PlaywrightFactory();
        page = pf.initBrowser("chrome");
       
    }

    @AfterMethod
    public void tearDown() {
        pf.closeBrowser();
    }
    
    
}
