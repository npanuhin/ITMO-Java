package markup;


import java.util.List;
import java.util.stream.Collectors;


public class Header extends AbstractContainer {
    private int level;

    public Header(List<AbstractContainerItem> items, int level) {
        super(items);
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format(
            "%s(level=%s, {%s})",
            getClass().getSimpleName(),
            level,
            items.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(", "))
        );
    }

    public void toMarkdown(StringBuilder builder) {
        for (int i = 0; i < level; i++) {
            builder.append('#');
        }
        builder.append(' ');
        toMarkdown(builder, "", "");
    }

    public void toHtml(StringBuilder builder) {
        toHtml(
            builder,
            "<h" + level + ">",
            "</h" + level + ">"
        );
    }
}
