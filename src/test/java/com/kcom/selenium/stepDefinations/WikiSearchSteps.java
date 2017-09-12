package com.kcom.selenium.stepDefinations;
import com.kcom.selenium.wikipedia.pages.LandingPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import objectRepo.ObjectRepository;
import test.AbstractTest;
import java.io.IOException;

public class WikiSearchSteps extends AbstractTest {
    public static final String INPUT_SEARCH_TEXT = "English";

    LandingPage landingPage;
    ObjectRepository objRepo;

    public WikiSearchSteps() throws IOException {
        landingPage = new LandingPage(driver);
        objRepo = new ObjectRepository();
    }

    @Before
    public void setUp() throws IOException, InterruptedException {
        super.setUp();
    }

    @After
    public void close() {
        super.close();
    }

    @Given("^user is on landing page$")
    public void goToLandingPage() throws Throwable {
        landingPage.goToWikiPediaPage(driver, url);

    }

    @When("^I enter search string english in search text box$")
    public void searchInputText() throws Throwable {
        landingPage.setInputSearchTextValue(driver, objRepo.getInputSearchTextBox(), INPUT_SEARCH_TEXT);
    }

    @Then("^I should see data populated text as english$")
    public void isSearchTextPopulated() throws Throwable {
    }

    @Then("^I select english as the search language$")
    public void selectEnglishLanguage() throws Throwable {
        landingPage.selectEnglishLangauge(driver, objRepo.getSearchLangauge(), objRepo.getLanguageSymbol());
    }

    @Then("^I click search button$")
    public void clickSearchButton() throws Throwable {
        landingPage.clickSearchButton(driver, objRepo.getSearchButtonRef());
    }

    @Then("^I should see search page heading as english$")
    public void verifySearchResultsHeadingPage() throws Throwable {
    }

    @Then("^I check search results displayed in english$")
    public void isSearchResultsDisplayedInEnglish() throws Throwable {
    }

    @Then("^I navigate search results page$")
    public void goToResultsPage() throws Throwable {
    }

    @Then("^I verify search results$")
    public void verifySearchResults() throws Throwable {
    }
}
