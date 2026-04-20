package pages;

import com.microsoft.playwright.Page;

public abstract class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void waitForSelector(String selector) {
        page.waitForSelector(selector);
    }
}