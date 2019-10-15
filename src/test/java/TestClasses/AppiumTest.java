package TestClasses;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"/Users/rich-gupta/IdeaProjects/BaseFramework/src/test/resources/features"},
        glue = {"StepDefinitions"},
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "html:target/cucumber-reports/",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        }
)

public class AppiumTest extends AbstractTestNGCucumberTests {
//    private TestNGCucumberRunner testNGCucumberRunner;

//    @BeforeClass
//    public void setUp(){
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//    }
//
//    @Test(priority = 0, description = "Open Rummy Circle", groups = "cucumber", dataProvider = "scenarios")
//    @Description("Open Rummy Circle")
//    @Severity(SeverityLevel.BLOCKER)
//    @Story("Appium Start")
//    public void testCal(CucumberFeatureWrapper cucumberFeatureWrapper) {
//        testNGCucumberRunner.runCucumber(cucumberFeatureWrapper.getCucumberFeature());
//    }
//
//    @DataProvider
//    public Object[][] scenarios() {
//        return testNGCucumberRunner.provideFeatures();
//    }
//
//    @AfterClass
//    public void teardown(){
//        testNGCucumberRunner.finish();
//    }
}
