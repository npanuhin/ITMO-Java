package markup;

import java.util.List;


public class Emphasis extends AbstractContainer implements AbstractContainerItem {
    
    public Emphasis(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        toMarkdown(builder, "*", "*");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        toHtml(builder, "<em>", "</em>");
    }
}
