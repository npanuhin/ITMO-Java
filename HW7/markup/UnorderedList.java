package markup;

import java.util.List;


public class UnorderedList extends AbstractList {

    public UnorderedList(final List<ListItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(final StringBuilder builder) {
        toMarkdown(builder, "<ul>", "</ul>");
    }

    @Override
    public void toHtml(final StringBuilder builder) {
        toHtml(builder, "<ul>", "</ul>");
    }
}
