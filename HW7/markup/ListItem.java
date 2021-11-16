package markup;

import java.util.List;


public class ListItem {
    protected List<AbstractListed> items;

    public ListItem(List<AbstractListed> items) {
        this.items = items;
    }

    public void toMarkdown(StringBuilder builder) {
        builder.append("<li>");
        for (AbstractListed item : items) {
            item.toMarkdown(builder);
        }
        builder.append("</li>");
    }

    public void toHtml(StringBuilder builder) {
        builder.append("<li>");
        for (AbstractListed item : items) {
            item.toHtml(builder);
        }
        builder.append("</li>");
    }
}
