package markup;

import java.util.List;


public class Insertion extends AbstractContainer implements AbstractContainerItem {

    public Insertion(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        toMarkdown(builder, "<<", ">>");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        toHtml(builder, "<ins>", "</ins>");
    }
}
