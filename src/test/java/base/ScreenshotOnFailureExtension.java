package base;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import utils.ScreenshotHelper;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof BaseTest baseTest) {
            boolean testFailed = context.getExecutionException().isPresent();

            if (testFailed && baseTest.page != null) {
                String testName = context.getRequiredTestMethod().getName();
                ScreenshotHelper.capture(baseTest.page, testName);
            }
        }
    }
}