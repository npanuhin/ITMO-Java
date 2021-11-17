package md2html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
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

import java.lang.reflect.Constructor;

import markup.*;


public class Md2Html {

    private static void rtrimStringBuilder(StringBuilder s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        s.setLength(i + 1);
    }

    private final static String[] markdownTags = new String[]{"*", "_", "**", "__", "--", "`", "<<", "}}"};
    private final static Map<String, Integer> tagIndexes = new HashMap<>();
    private final static int tagCount = markdownTags.length;
    private final static ArrayList<Class<? extends AbstractContainerItem>> markupElements = new ArrayList<>(
        Arrays.asList(
            Emphasis.class, Emphasis.class, Strong.class, Strong.class,
            Strikeout.class, Code.class, Insertion.class, Deletion.class
        )
    );
    private final static Map<String, Class<? extends AbstractContainerItem>> markupByTag = new HashMap<>();

    static {
        for (int i = 0; i < tagCount; i++) {
            tagIndexes.put(markdownTags[i], i);
            markupByTag.put(markdownTags[i], markupElements.get(i));
        }
        tagIndexes.put(">>", tagIndexes.get("<<"));
        tagIndexes.put("{{", tagIndexes.get("}}"));
        markupByTag.put(">>", markupByTag.get("<<"));
        markupByTag.put("{{", markupByTag.get("{{"));
    }

    private static int getTagIndex(String text, int startIndex) {
        int res = tagIndexes.getOrDefault(text.substring(startIndex, Math.min(startIndex + 2, text.length())), -1);
        if (res == -1) {
            res = tagIndexes.getOrDefault(String.valueOf(text.charAt(startIndex)), -1);
        }
        return res;
    }

    private static ArrayList<AbstractContainerItem> parseInlineMd(String text) throws ReflectiveOperationException {
        ArrayList<AbstractContainerItem> result = new ArrayList<>();

        IntList[] tagsInText = new IntList[tagCount];
        for (int i = 0; i < tagCount; i++) {
            tagsInText[i] = new IntList();
        }

        int tagIndex;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\\') {
                i++;
                continue;
            }
            tagIndex = getTagIndex(text, i);
            if (tagIndex != -1) {
                tagsInText[tagIndex].add(i);
            }
        }

        for (IntList tagInText : tagsInText) {
            if (tagInText.size % 2 == 1) {
                tagInText.size--;
            }
            tagInText.reverse();
        }

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\\') {
                if (i >= text.length()) {
                    buffer.append(c);
                    continue;
                }
                c = text.charAt(++i);

            } else {
                tagIndex = getTagIndex(text, i);

                if (tagIndex != -1) {
                    while (!tagsInText[tagIndex].isEmpty() && tagsInText[tagIndex].back() < i) {
                        tagsInText[tagIndex].size--;
                    }

                    if (!tagsInText[tagIndex].isEmpty()) {
                        i += markdownTags[tagIndex].length();
                        tagsInText[tagIndex].size--;
                        int end = tagsInText[tagIndex].pop();

                        if (!buffer.isEmpty()) {
                            result.add(new Text(buffer.toString()));
                            buffer.setLength(0);
                        }

                        Constructor constructor = markupByTag.get(markdownTags[tagIndex]).getConstructors()[0];
                        result.add(
                            (AbstractContainerItem) constructor.newInstance(parseInlineMd(text.substring(i, end)))
                        );

                        i = end + markdownTags[tagIndex].length() - 1;
                        continue;
                    }
                }
            }

            switch (c) {
                case '<':
                    buffer.append("&lt;");
                    continue;
                case '>':
                    buffer.append("&gt;");
                    continue;
                case '&':
                    buffer.append("&amp;");
                    continue;
            }

            buffer.append(c);
        }

        if (!buffer.isEmpty()) {
            result.add(new Text(buffer.toString()));
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<AbstractElement> structure = new ArrayList<>();

        // Input:
        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    "utf-8"
                )
            )
        ) {
            try {
                StringBuilder block = new StringBuilder();
                String line = "";

                while (line != null && (line = reader.readLine()) != null) {
                    while (line != null && !line.isEmpty()) {
                        block.append(line).append('\n');
                        line = reader.readLine();
                    }

                    if (!block.isEmpty()) {
                        rtrimStringBuilder(block);
                        
                        int pos = 0;
                        while (pos < block.length() && block.charAt(pos) == '#') {
                            pos++;
                        }

                        if (pos > 0 && pos < block.length() && Character.isWhitespace(block.charAt(pos))) {
                            int headerLvl = pos;
                            while (Character.isWhitespace(block.charAt(pos))) {
                                pos++;
                            }
                            structure.add(new Header(parseInlineMd(block.toString().substring(pos)), headerLvl));

                        } else {
                            structure.add(new HtmlParagraph(parseInlineMd(block.toString())));
                        }

                        block.setLength(0);
                    }
                }
            } catch (ReflectiveOperationException e) {
                System.out.println("Java reflection error: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open input file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot read input file: " + e.getMessage());
        }


        // System.err.println(structure);


        // Output:
        try (
            BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf-8"
                )
            );
        ) {
            StringBuilder result = new StringBuilder();
            for (AbstractElement block : structure) {
                block.toHtml(result);
                result.append('\n');
            }
            output.write(result.toString());

        } catch (IOException e) {
            System.out.println("Cannot write to output file: " + e.getMessage());
        }
    }
}
