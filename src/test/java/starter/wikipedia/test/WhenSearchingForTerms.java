package starter.wikipedia.test;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import starter.wikipedia.ui.DisplayedArticle;
import starter.wikipedia.ui.NavigateActions;
import starter.wikipedia.ui.SearchActions;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
class WhenSearchingForTerms {

    /**
     * Define the webdriver instance to be used for these tests
     */
    @Managed
    WebDriver driver;

    /**
     * Navigation actions. This is a UIInteraction class so it will be instantiated automatically by Serenity.
     */
    NavigateActions navigate;

    /**
     * Actions related to searches. This is a UIInteraction class so it will be instantiated automatically by Serenity.
     */
    SearchActions search;

    /**
     * A page object representing a Wikipedia article that is currently appearing in the browser.
     * Page Objects are automatically initialised by Serenity.
     */
    DisplayedArticle displayedArticle;

    @ParameterizedTest
    @CsvFileSource(resources = "/data/data.csv", numLinesToSkip = 1, delimiter = ';')
    void searchBySingleKeyword(String terme, String attendu) {
        navigate.toTheHomePage();
        search.searchBy(terme);
        Serenity.reportThat("The first heading should be 'Mount Everest'",
                () -> assertThat(displayedArticle.getFirstHeading()).isEqualTo(attendu)
        );
    }
}
