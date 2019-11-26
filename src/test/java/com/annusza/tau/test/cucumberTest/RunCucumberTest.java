package com.annusza.tau.test.cucumberTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/test/resources/BooksSearching.feature", "src/test/resources/DeleteBookFromList.feature"})
public class RunCucumberTest {
}