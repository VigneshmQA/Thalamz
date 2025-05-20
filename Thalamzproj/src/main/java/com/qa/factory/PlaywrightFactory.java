package com.qa.factory;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public Page initBrowser(String browserName) {
        playwright = Playwright.create();
        switch (browserName.toLowerCase()) {
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browserName);
        }
        context = browser.newContext();
        page = context.newPage();
        return page;
    }

    public void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
    }
}
