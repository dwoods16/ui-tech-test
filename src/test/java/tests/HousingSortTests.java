package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.options.LoadState;
import pages.HousingPage;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HousingSortTests extends BaseTest {

    private static final String BASE_URL = "https://madrid.craigslist.org/search/hhh?lang=en&cc=gb";

    private HousingPage openHousingPage(String url) {
        page.navigate(url);
        page.waitForLoadState(LoadState.NETWORKIDLE);
        return new HousingPage(page);
    }

    @Test
    public void defaultSortOptions() {
        HousingPage housingPage = openHousingPage(BASE_URL);
        List<String> expectedSortOptions = List.of("newest", "oldest", "£ → £££", "£££ → £", "upcoming");

        assertEquals(expectedSortOptions, housingPage.getSortOptions(),
                "Expected sort options do not match actual sort options.");
    }

    @Test
    public void sortOptionsAfterSearch() {
        HousingPage housingPage = openHousingPage(BASE_URL);
        housingPage.searchHousing("house");
        List<String> expectedSearchSortOptions = List.of("newest", "oldest", "£ → £££", "£££ → £", "relevance",
                "upcoming");

        assertEquals(expectedSearchSortOptions, housingPage.getSortOptions(),
                "Expected sort options after search do not match actual sort options.");
    }

    @Test
    public void sortPriceLowToHigh() {
        HousingPage housingPage = openHousingPage(BASE_URL);
        housingPage.sortByPriceLowToHigh();
        List<Integer> prices = housingPage.getListingPrices();
        
        assertTrue(housingPage.isSortedLowToHigh(prices),
                "Expected prices sorted low to high but was: " + prices);
    }

    @Test
    public void sortPriceHighToLow() {
        HousingPage housingPage = openHousingPage(BASE_URL);
        housingPage.sortByPriceHighToLow();
        List<Integer> prices = housingPage.getListingPrices();
        
        assertTrue(housingPage.isSortedHighToLow(prices),
                "Expected prices sorted high to low but was: " + prices);
    }
}