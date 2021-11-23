package markup;


import java.util.List;
import java.util.stream.Collectors;


public class Header extends AbstractContainer {
    private int level;

    public Header(final List<AbstractContainerItem> items, final int level) {
        super(items);
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format(
            "%s(level=%s, {%s})",
            getClass().getSimpleName(),
            level,
            items.stream().map(String::valueOf).collect(Collectors.joining(", "))
        );
    }

    @Override
    public void toMarkdown(final StringBuilder builder) {
        builder.append("#".repeat(level));
        builder.append(' ');
        toMarkdown(builder, "", "");
    }

    @Override
    public void toHtml(final StringBuilder builder) {
        toHtml(
            builder,
            "<h" + level + ">",
            "</h" + level + ">"
        );
    }
}
