package utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotHelper {

    private static final String SCREENSHOT_DIR = "test-output/screenshots";

    public static String capture(Page page, String testName) {
        try {
            Path directory = Paths.get(SCREENSHOT_DIR);
            Files.createDirectories(directory);
            Path screenshotPath = directory.resolve(testName + ".png");
            byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
            Allure.addAttachment(testName, "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
            return screenshotPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot for test: " + testName, e);
        }
    }
}