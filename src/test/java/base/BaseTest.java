package base;

import com.microsoft.playwright.Page;
import config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseTest {

    protected Page page;

    @BeforeEach
    public void setUp() {
        TestConfig.init();
        page = TestConfig.getPage();
    }

    @AfterEach
    public void tearDown() {
        TestConfig.close();
    }
}