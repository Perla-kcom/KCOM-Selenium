package com.kcom.selenium.stepDefinations;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import test.AbstractTest;

import java.io.IOException;

public class TimeTablesSteps extends AbstractTest {

    @Before
    public void setUp() throws IOException, InterruptedException {
        super.setUp();
    }

    @After
    public void close() {
        super.close();
    }

    @Given("^I navigate to Timetable Trasnscation Page$")
    public void goToTimeTableTranscationPage() throws Throwable {
        logger.info("Time Tables Page");

    }

    @Then("^A list of all \"([^\"]*)\" schedule updates are displayed date descending$")
    public void a_list_of_all_schedule_updates_are_displayed_date_descending(String status) throws Throwable {
        logger.info("I see data");

    }


}
