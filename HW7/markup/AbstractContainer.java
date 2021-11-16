package markup;

import java.util.List;
import java.util.stream.Collectors;


abstract class AbstractContainer implements AbstractElement {
    protected List<AbstractContainerItem> items;

    public AbstractContainer(List<AbstractContainerItem> items) {
        this.items = items;
    }

    public String toString() {
        return String.format(
            "%s(%s)",
            getClass().getSimpleName(),
            items.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(", "))
        );
    }

    public void toMarkdown(StringBuilder builder, String opening, String closing) {
        builder.append(opening);
        for (AbstractContainerItem item : items) {
            item.toMarkdown(builder);
        }
        builder.append(closing);
    }

    public void toHtml(StringBuilder builder, String openingTag, String closingTag) {
        builder.append(openingTag);
        for (AbstractContainerItem item : items) {
            item.toHtml(builder);
        }
        builder.append(closingTag);
    }
}