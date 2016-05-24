package nmccabe;

import java.util.List;
import java.util.stream.Collectors;

public class HTMLifier {

    public String getHtmlStart() {
        return getHtmlStart("Directory");
    }

    public String getHtmlStart(String pageTitle) {
        return    "<HTML>"
                + "<HEAD>"
                + "<title>" + pageTitle +"</title>"
                + "<h1>" + pageTitle + "</h1></HEAD>"
                + "<body>";
    }

    public String getHTMLEnd() {
        return "</body>" + "</HTML>";
    }
    
    public String asUnorderedListOfLinks(List<String> listElements) {
        List<String> links = listElements.stream().map(this::formatAsLink).collect(Collectors.toList());
        return asUnorderedList(links);
    }

    public String formatAsLink(String fileName) {
        return "<a href=\"/" + fileName + "\">" + fileName + "</a>";
    }

    public String asUnorderedList(List<String> listElements) {
        StringBuilder stringIncubator = new StringBuilder();
        stringIncubator.append("<ul>");

        for (String listElement:listElements) {
            stringIncubator.append("<li>");
            stringIncubator.append(listElement);
            stringIncubator.append("</li>");
            stringIncubator.append("</br>");
        }

        stringIncubator.append("</ul>");

        return stringIncubator.toString();
    }

}
