package markup;

import java.util.List;


public class Strong extends AbstractContainer implements AbstractContainerItem {
    
    public Strong(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder, "__", "__");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        super.toHtml(builder, "<strong>", "</strong>");
    }
}
