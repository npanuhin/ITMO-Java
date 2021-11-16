package markup;


public interface AbstractElement {
    String toString();
    void toMarkdown(StringBuilder builder);
    void toHtml(StringBuilder builder);
}