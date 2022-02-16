package markup;

import java.util.List;


public class Deletion extends AbstractContainer implements AbstractContainerItem {

    public Deletion(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        toMarkdown(builder, "}}", "{{");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        toHtml(builder, "<del>", "</del>");
    }
}
