package markup;

import java.util.List;


public class Strikeout extends AbstractContainer implements AbstractContainerItem {

    public Strikeout(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        toMarkdown(builder, "~", "~");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        toHtml(builder, "<s>", "</s>");
    }
}
