package markup;

import java.util.List;


public class Emphasis extends AbstractContainer implements AbstractContainerItem {
    
    public Emphasis(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder, "*", "*");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        super.toHtml(builder, "<em>", "</em>");
    }
}
