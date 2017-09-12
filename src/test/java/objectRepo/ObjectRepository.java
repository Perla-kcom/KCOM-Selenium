package objectRepo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ObjectRepository {
    protected Properties properties = null;
    protected InputStream input = ObjectRepository.class.getClassLoader().getResourceAsStream("objectRepo/object.properties");

    public ObjectRepository() throws IOException {
        properties = new Properties();
        properties.load(input);
    }

    public String getUserName() {
        return properties.getProperty("userName");
    }

    public String getPassword() {
        return properties.getProperty("passwordd");
    }

    public String getLoginButton() {
        return properties.getProperty("loginButton");
    }
    public String getInputSearchTextBox() {
        return properties.getProperty("searchTextBox");
    }

    public String getSearchLangauge() {
        return properties.getProperty("searchLanguage");
    }

    public String getSearchButtonRef() {
        return properties.getProperty("searchbutton");
    }

    public String getEnglishPageFirstHeadingTitle() {
        return properties.getProperty("firstheadingTitle");
    }

    public String getLanguageSymbol() {
        return properties.getProperty("languageiconsymbol");
    }

    public String getTimeTableButton() {
        return properties.getProperty("timetableButton");
    }

    public String getFirstButton() {
        return properties.getProperty("firstButton");
    }

    public String getPreviousButton() {
        return properties.getProperty("previousButton");
    }

    public String getNextButton() {
        return properties.getProperty("nextButton");
    }

    public String getLastButton() {
        return properties.getProperty("lastButton");
    }
}
