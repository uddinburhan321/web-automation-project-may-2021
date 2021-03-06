package com.amazon.tests;

import com.amazon.data.DataProvidersForTest;
import com.amazon.pages.HomePage;
import com.pnt.base.ConnectDB;
import com.pnt.base.TestBase;
import com.pnt.base.report.ExtentTestManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchTests extends TestBase {

    // BeforeSuite

    // BeforeMethod
    // method
    // AfterMethod

    // BeforeMethod
    // method
    // AfterMethod

    // AfterSuite

    private static Logger LOGGER = Logger.getLogger(SearchTests.class);
    private HomePage homePage;

    @BeforeMethod
    public void setUpPOM() {
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test(enabled = false)// tomorrow hw : to move all of your existing test cases
    public void validateUserBeingAbleToSearchForAnItem() {
        validateUrlWithExpected("https://amazon.com");

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java Books");
        ExtentTestManager.log("Java Books typed in the search bar", LOGGER);

        driver.findElement(By.id("nav-search-submit-button")).click();
        ExtentTestManager.log("Clicked on the search button", LOGGER);
    }

    @Test(groups = "", enabled = false)
    // next week hw : to move all of your existing test cases to use Page Object Model
    // & or develop at least 20 test cases combination of test driven and data driven
    public void validateUserBeingAbleToSearchForJavaBooks() {
        homePage.typeOnSearchBar("Java Books");
        ExtentTestManager.log("Java Books typed in the search bar", LOGGER);

        homePage.clickOnSearchButton();
        ExtentTestManager.log("Clicked on the search button", LOGGER);
    }

    @Test(enabled = false, dataProviderClass = DataProvidersForTest.class, dataProvider = "getDataForSearchTest")
    public void validateUserBeingAbleToSearchForBooks(String bookName) {
        homePage.typeOnSearchBar(bookName);
        ExtentTestManager.log(bookName + " typed in the search bar", LOGGER);

        homePage.clickOnSearchButton();
        ExtentTestManager.log("Clicked on the search button", LOGGER);
    }

    @Test(enabled = true)
    public void validateUserBeingAbleToSearchForAnItemFromDatabase() throws SQLException {
        String query = "select * from testdata;";
        ArrayList<String> datas = ConnectDB.connectToDbAndGetData(query, "bookName");

        homePage.typeOnSearchBar(datas.get(0));
        ExtentTestManager.log("Java Books typed in the search bar", LOGGER);

        homePage.clickOnSearchButton();
        ExtentTestManager.log("Clicked on the search button", LOGGER);

    }


}
