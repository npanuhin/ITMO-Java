package markup;


public class Text implements AbstractContainerItem {
    private String value = "";

    public Text(String value) {
        this.value = value;
    }

    public String toString() {
        return String.format("Text(\"%s\")", value);
    }

    public void toMarkdown(StringBuilder builder) {
        builder.append(value);
    }

    public void toHtml(StringBuilder builder) {
        builder.append(value);
    }
}
