package md2html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;

import markup.*;


public class Md2Html {
    private static void rtrimStringBuilder(final StringBuilder s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        s.setLength(i + 1);
    }

    private static final String[] MARKDOWN_TAGS = {"*", "_", "**", "__", "--", "`", "<<", "}}"};
    private static final Map<String, Integer> tagIndexes = new HashMap<>();

    static {
        for (int i = 0; i < MARKDOWN_TAGS.length; i++) {
            tagIndexes.put(MARKDOWN_TAGS[i], i);
        }
        tagIndexes.put(">>", tagIndexes.get("<<"));
        tagIndexes.put("{{", tagIndexes.get("}}"));
    }

    private static int getTagIndex(final String text, final int startIndex) {
        final int res = tagIndexes.getOrDefault(text.substring(startIndex, Math.min(startIndex + 2, text.length())), -1);
        if (res != -1) {
            return res;
        }

        return tagIndexes.getOrDefault(String.valueOf(text.charAt(startIndex)), -1);
    }

    private static List<AbstractContainerItem> parseInlineMd(final String text) {
        final List<AbstractContainerItem> result = new ArrayList<>();

        final List<IntList> tagsInText = new ArrayList<>();
        for (int i = 0; i < MARKDOWN_TAGS.length; i++) {
            tagsInText.add(new IntList());
        }

        int tagIndex;
        for (int i = 0; i < text.length();) {
            if (text.charAt(i) == '\\') {
                i += 2;
                continue;
            }
            tagIndex = getTagIndex(text, i);
            if (tagIndex != -1) {
                tagsInText.get(tagIndex).add(i);
                i += MARKDOWN_TAGS[tagIndex].length();
            } else {
                i++;
            }
        }

        for (final IntList tagInText : tagsInText) {
            if (tagInText.size % 2 == 1) {
                tagInText.size--;
            }
            tagInText.reverse();
        }

        final StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\\') {
                if (i >= text.length() - 1) {
                    buffer.append(c);
                    continue;
                }
                c = text.charAt(i);
                continue;

            } else {
                tagIndex = getTagIndex(text, i);

                if (tagIndex != -1) {
                    final IntList tagIndices = tagsInText.get(tagIndex);
                    while (!tagIndices.isEmpty() && tagIndices.back() < i) {
                        tagIndices.size--;
                    }

                    if (!tagIndices.isEmpty()) {
                        final String tag = MARKDOWN_TAGS[tagIndex];
                        i += tag.length();
                        tagIndices.size--;
                        final int end = tagIndices.pop();

                        if (!buffer.isEmpty()) {
                            result.add(new Text(buffer.toString()));
                            buffer.setLength(0);
                        }

                        addMarkupObj(result, tag, parseInlineMd(text.substring(i, end)));

                        i = end + tag.length() - 1;
                        continue;
                    }
                }
            }

            addHtmlChar(buffer, c);
        }

        if (!buffer.isEmpty()) {
            result.add(new Text(buffer.toString()));
        }

        return result;
    }

    private static void addMarkupObj(
        final List<AbstractContainerItem> result,
        final String tag,
        final List<AbstractContainerItem> inner
    ) {
        switch (tag) {
            case "*":
            case "_":
                result.add(new Emphasis(inner));
                break;
            case "**":
            case "__":
                result.add(new Strong(inner));
                break;
            case "--":
                result.add(new Strikeout(inner));
                break;
            case "`":
                result.add(new Code(inner));
                break;
            case "<<":
                result.add(new Insertion(inner));
                break;
            case "}}":
                result.add(new Deletion(inner));
                break;
        }
    }

    private static void addHtmlChar(final StringBuilder buffer, final char c) {
        switch (c) {
            case '<':
                buffer.append("&lt;");
                break;
            case '>':
                buffer.append("&gt;");
                break;
            case '&':
                buffer.append("&amp;");
                break;
            default:
                buffer.append(c);
        }
    }

    public static void main(final String[] args) {
        final List<AbstractElement> paragraphs = new ArrayList<>();

        // Input:
        try (
                final BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    "utf-8"
                )
            )
        ) {
            final StringBuilder block = new StringBuilder();
            String line = "";

            while (line != null && (line = reader.readLine()) != null) {
                while (line != null && !line.isEmpty()) {
                    block.append(line).append('\n');
                    line = reader.readLine();
                }

                if (!block.isEmpty()) {
                    rtrimStringBuilder(block);

                    paragraphs.add(convertParagraph(block.toString()));

                    block.setLength(0);
                }
            }
        } catch (final FileNotFoundException e) {
            System.out.println("Cannot open input file: " + e.getMessage());
        } catch (final IOException e) {
            System.out.println("Cannot read input file: " + e.getMessage());
        }


        // System.err.println(structure);


        // Output:
        try (
                final BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf-8"
                )
            );
        ) {
            final StringBuilder result = new StringBuilder();
            for (final AbstractElement block : paragraphs) {
                block.toHtml(result);
                result.append('\n');
            }
            output.write(result.toString());

        } catch (final IOException e) {
            System.out.println("Cannot write to output file: " + e.getMessage());
        }
    }

    private static AbstractElement convertParagraph(final String paragraph) {
        int pos = 0;
        while (pos < paragraph.length() && paragraph.charAt(pos) == '#') {
            pos++;
        }

        if (pos > 0 && pos < paragraph.length() && Character.isWhitespace(paragraph.charAt(pos))) {
            final int headerLvl = pos;
            while (Character.isWhitespace(paragraph.charAt(pos))) {
                pos++;
            }
            return new Header(parseInlineMd(paragraph.substring(pos)), headerLvl);
        } else {
            return new HtmlParagraph(parseInlineMd(paragraph));
        }
    }
}
