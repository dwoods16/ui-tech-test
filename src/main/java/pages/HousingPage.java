package pages;

import com.microsoft.playwright.Page;
import java.util.List;
import java.util.stream.Collectors;

public class HousingPage extends BasePage {

    private static final String SORT_DROPDOWN = ".cl-search-sort-mode";
    private static final String SORT_DROPDOWN_MENU = "div.bd-for-bd-combo-box.bd-list-box";
    private static final String SORT_OPTIONS = "div.items button.bd-button span.label";
    private static final String SEARCH_TEXT_FIELD = "input[enterkeyhint='Search']";
    private static final String SEARCH_BUTTON = "button[type='submit']";
    private static final String PRICE_LOW_TO_HIGH = "button.cl-search-sort-mode-price-asc";
    private static final String PRICE_HIGH_TO_LOW = "button.cl-search-sort-mode-price-desc";
    private static final String LISTING_ITEM = "[data-testid='listing-item']";
    private static final String PRICE_SELECTORS = ".priceinfo";

    public HousingPage(Page page) {
        super(page);
    }

    private void selectSortOption(String option) {
        page.click(SORT_DROPDOWN);
        page.click(option);
    }

    public List<String> getSortOptions() {
        page.waitForSelector(PRICE_SELECTORS);
        // page.waitForSelector(SORT_DROPDOWN);
        page.locator(SORT_DROPDOWN).first().click();
        page.waitForSelector(SORT_DROPDOWN_MENU);
        return page.locator(SORT_OPTIONS).allTextContents().stream()
                .collect(Collectors.toList());
    }

    public void searchHousing(String searchTerm) {
        page.fill(SEARCH_TEXT_FIELD, searchTerm);
        page.click(SEARCH_BUTTON);
        page.waitForURL("**/search/hhh?query=" + searchTerm + "**");
        page.waitForSelector(SORT_DROPDOWN);
    }

    public void sortByPriceLowToHigh() {
        selectSortOption(PRICE_LOW_TO_HIGH);
        page.waitForURL("**/search/hhh?sort=priceasc**");
    }

    public void sortByPriceHighToLow() {
        selectSortOption(PRICE_HIGH_TO_LOW);
        page.waitForURL("**/search/hhh?sort=pricedsc**");
    }

    public List<Integer> getListingPrices() {
        return page.locator(LISTING_ITEM).all().stream()
                .map(item -> item.locator(PRICE_SELECTORS).textContent())
                .map(price -> Integer.parseInt(price.replaceAll("[^0-9]", "")))
                .collect(Collectors.toList());
    }

    public boolean isSortedLowToHigh(List<Integer> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedHighToLow(List<Integer> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}