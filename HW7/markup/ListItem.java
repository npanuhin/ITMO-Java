package markup;

import java.util.List;


public class ListItem {
    protected List<AbstractListed> items;

    public ListItem(final List<AbstractListed> items) {
        this.items = items;
    }

    public void toMarkdown(final StringBuilder builder) {
        builder.append("<li>");
        for (final AbstractListed item : items) {
            item.toMarkdown(builder);
        }
        builder.append("</li>");
    }

    public void toHtml(final StringBuilder builder) {
        builder.append("<li>");
        for (final AbstractListed item : items) {
            item.toHtml(builder);
        }
        builder.append("</li>");
    }
}
