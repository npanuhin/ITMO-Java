package markup;

import java.util.List;
import java.util.stream.Collectors;


abstract class AbstractList implements AbstractListed {

    protected List<ListItem> items;

    public AbstractList(List<ListItem> items) {
        this.items = items;
    }

    public String toString() {
        return String.format(
            "%s(%s)",
            getClass().getSimpleName(),
            items.stream().map(String::valueOf).collect(Collectors.joining(", "))
        );
    }

    public void toMarkdown(StringBuilder builder, String opening, String closing) {
        builder.append(opening);
        for (ListItem item : items) {
            item.toMarkdown(builder);
        }
        builder.append(closing);
    }

    public void toHtml(StringBuilder builder, String openingTag, String closingTag) {
        builder.append(openingTag);
        for (ListItem item : items) {
            item.toHtml(builder);
        }
        builder.append(closingTag);
    }
}
