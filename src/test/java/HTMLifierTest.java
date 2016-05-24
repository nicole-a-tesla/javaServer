import nmccabe.HTMLifier;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HTMLifierTest {
    HTMLifier HTMLifier;

    @Before
    public void setUp() {
        HTMLifier = new HTMLifier();
    }
    @Test
    public void formatsFileNameAsLink() throws Exception {
        String actualLink = HTMLifier.formatAsLink("hi");
        String expectedLink ="<a href=\"/hi\">hi</a>";
        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void formatsOfThings() throws Exception {
        List<String> things = Arrays.asList("thing1", "thing2");
        String formattedThings = HTMLifier.asUnorderedList(things);
        String expectedFormattedThings = "<ul>"
                                        + "<li>" + "thing1" + "</li>"
                                        + "</br>"
                                        + "<li>" + "thing2" + "</li>"
                                        + "</br>"
                                        + "</ul>";
        assertEquals(expectedFormattedThings, formattedThings);
    }

    @Test
    public void formatsAListOfLinks() throws Exception {
        List<String> things = Arrays.asList("thing1", "thing2");
        String formattedThings = HTMLifier.asUnorderedListOfLinks(things);
        String expectedFormattedThings = "<ul>"
                                        + "<li>" + "<a href=\"/thing1\">thing1</a>" + "</li>"
                                        + "</br>"
                                        + "<li>" + "<a href=\"/thing2\">thing2</a>" + "</li>"
                                        + "</br>"
                                        + "</ul>";
        assertEquals(expectedFormattedThings, formattedThings);
    }

    @Test
    public void returnsCorrectHTMLStart() throws Exception {
        String pageTitle = "TITLE!";
        String expectedStart = "<HTML>" + "<HEAD>"
                             + "<title>" + pageTitle +"</title>"
                             + "<h1>" + pageTitle + "</h1></HEAD>"
                             + "<body>";
        assertEquals(expectedStart, HTMLifier.getHtmlStart(pageTitle));
    }

    @Test
    public void returnsDefaultTitleForHTMLStart() throws Exception {
        String expectedStart = "<HTML>" + "<HEAD>"
                             + "<title>" + "Directory" +"</title>"
                             + "<h1>" + "Directory" + "</h1></HEAD>"
                             + "<body>";
        assertEquals(expectedStart, HTMLifier.getHtmlStart());
    }

    @Test
    public void returnsCorrectHTMLEnd() throws Exception {
        String expectedEnd = "</body>" + "</HTML>";
        assertEquals(expectedEnd, HTMLifier.getHTMLEnd());
    }
}
