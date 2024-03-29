import java.util.List;

import markup.*;


public class Markup {
    public static void main(String[] args) {

        Paragraph paragraph = new Paragraph(List.of(
            new Strong(List.of(
                new Text("1"),
                new Strikeout(List.of(
                    new Text("2"),
                    new Emphasis(List.of(
                        new Text("3"),
                        new Text("4")
                    )),
                    new Text("5")
                )),
                new Text("6")
            ))
        ));

        System.out.println(paragraph);

        StringBuilder markdown = new StringBuilder();
        paragraph.toMarkdown(markdown);
        System.out.println(markdown);

        StringBuilder html = new StringBuilder();
        paragraph.toHtml(html);
        System.out.println(html);
    }
}
