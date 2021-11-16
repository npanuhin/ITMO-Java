package markup;

import java.util.List;


public class Paragraph extends AbstractContainer implements AbstractListed {

    public Paragraph(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        toMarkdown(builder, "", "");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        toHtml(builder, "", "");
    }
}
