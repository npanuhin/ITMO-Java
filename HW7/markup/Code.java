package markup;

import java.util.List;


public class Code extends AbstractContainer implements AbstractContainerItem {
    
    public Code(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder, "`", "`");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        super.toHtml(builder, "<code>", "</code>");
    }
}
