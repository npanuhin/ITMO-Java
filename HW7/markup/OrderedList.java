package markup;

import java.util.List;


public class OrderedList extends AbstractList {

    public OrderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder, "<ol>", "</ol>");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        super.toHtml(builder, "<ol>", "</ol>");
    }
}
