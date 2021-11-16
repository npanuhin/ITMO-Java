package markup;

import java.util.List;


public class UnorderedList extends AbstractList {

    public UnorderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder, "<ul>", "</ul>");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        super.toHtml(builder, "<ul>", "</ul>");
    }
}
