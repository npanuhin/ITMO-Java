package markup;

import java.util.List;


public class Code extends AbstractContainer implements AbstractContainerItem {

    public Code(final List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(final StringBuilder builder) {
        toMarkdown(builder, "`", "`");
    }

    @Override
    public void toHtml(final StringBuilder builder) {
        toHtml(builder, "<code>", "</code>");
    }
}
