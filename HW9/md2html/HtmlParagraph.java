package md2html;

import java.util.List;

import markup.*;


public class HtmlParagraph extends Paragraph {
    public HtmlParagraph(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toHtml(StringBuilder builder) {
        toHtml(builder, "<p>", "</p>");
    }
}
