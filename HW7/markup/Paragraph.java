package markup;

import java.util.List;


public class Paragraph extends AbstractContainer implements AbstractListed {

    public Paragraph(List<AbstractContainerItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        super.toMarkdown(builder, "", "");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        super.toHtml(builder, "", "");
    }
}
