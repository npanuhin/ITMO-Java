package markup;

import java.util.List;


public class Strong extends AbstractContainer implements AbstractContainerItem {

    public Strong(final List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(final StringBuilder builder) {
        toMarkdown(builder, "__", "__");
    }

    @Override
    public void toHtml(final StringBuilder builder) {
        toHtml(builder, "<strong>", "</strong>");
    }
}
