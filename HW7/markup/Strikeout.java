package markup;

import java.util.List;


public class Strikeout extends AbstractContainer implements AbstractContainerItem {
    
    public Strikeout(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder, "~", "~");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        super.toHtml(builder, "<s>", "</s>");
    }
}
