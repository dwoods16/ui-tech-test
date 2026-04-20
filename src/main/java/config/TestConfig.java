package config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TestConfig {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void init() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        context = browser.newContext();
        page = context.newPage();

    }

    public static Page getPage() {
        if (page == null) {
            init();
        }
        return page;
    }

    public static Playwright getPlaywright() {
        return playwright;
    }

    public static Browser getBrowser() {
        return browser;
    }

    public static BrowserContext getContext() {
        return context;
    }

    public static void close() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}