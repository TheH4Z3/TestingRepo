package com.restful.booker.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        glue = {"com.restful.booker.stepdefinitions", "com.restful.booker.setup"},
        features = "src/test/resources/features/nonfunctional",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "",
        plugin = {"pretty"}
)
public class Nonfunctional {
}
