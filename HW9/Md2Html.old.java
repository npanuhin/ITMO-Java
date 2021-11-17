package md2html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedWriter;

// import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;

import markup.*;


public class Md2Html {

    private static String rtrim(String s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        return s.substring(0, i + 1);
    }

    private static String[] markdownTag = new String[]{"*", "_", "**", "__", "--", "`", "<<", "}}"};
    private static Map<String, Integer> tagIndexes = new HashMap<>();
    private static ArrayList<Class<? extends AbstractElement>> markupElements = new ArrayList<>(Arrays.asList(
        Emphasis.class, Emphasis.class, Strong.class, Strong.class,
        Strikeout.class, Code.class, Insertion.class, Deletion.class
    ));
    private static Map<String, Class<? extends AbstractElement>> markupByTag = new HashMap<>();
    private static int tagCount = markdownTag.length;

    static {
        for (int i = 0; i < tagCount; i++) {
            tagIndexes.put(markdownTag[i], i);
            markupByTag.put(markdownTag[i], markupElements.get(i));
        }
        tagIndexes.put(">>", tagIndexes.get("<<"));
        markupByTag.put(">>", Insertion.class);
        tagIndexes.put("{{", tagIndexes.get("}}"));
        markupByTag.put("{{", Deletion.class);
    }

    private static int getTagIndex(String cur) {
        int res = tagIndexes.getOrDefault(cur, -1);
        if (res == -1) {
            res = tagIndexes.getOrDefault(Character.toString(cur.charAt(0)), -1);
        }
        return res;
    }

    private static String parseInlineMarkdown(String text) throws ReflectiveOperationException {
        StringBuilder result = new StringBuilder();

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
            tagIndex = getTagIndex(text.substring(i, Math.min(i + 2, text.length())));
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

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\\') {
                if (i >= text.length()) {
                    result.append(c);
                    continue;
                }
                c = text.charAt(++i);

            } else {
                tagIndex = getTagIndex(text.substring(i, Math.min(i + 2, text.length())));

                if (tagIndex != -1) {
                    while (!tagsInText[tagIndex].isEmpty() && tagsInText[tagIndex].back() < i) {
                        tagsInText[tagIndex].size--;
                    }

                    if (!tagsInText[tagIndex].isEmpty()) {
                        tagsInText[tagIndex].size--;
                        int end = tagsInText[tagIndex].pop();
                        i += markdownTag[tagIndex].length();

                        Class<? extends AbstractElement> markupType = markupByTag.get(markdownTag[tagIndex]);
                        Constructor constructor = markupType.getConstructors()[0];
                        AbstractElement elem = (AbstractElement) constructor.newInstance(List.of(
                            new Text(parseInlineMarkdown(text.substring(i, end)))
                        ));
                        elem.toHtml(result);

                        i = end + markdownTag[tagIndex].length() - 1;
                        continue;
                    }
                }
            }

            switch (c) {
                case '<':
                    result.append("&lt;");
                    continue;
                case '>':
                    result.append("&gt;");
                    continue;
                case '&':
                    result.append("&amp;");
                    continue;
            }

            result.append(c);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ArrayList<AbstractElement> structure = new ArrayList<>();

        // Input:
        try {
            Scanner input = new Scanner(new FileInputStream(args[0]));

            try {
                StringBuilder block = new StringBuilder();
                String line;

                while (input.canRead() || !block.isEmpty()) {
                    if (input.canRead()) {
                        line = input.nextLine();
                        input.skipLine();
                    } else {
                        line = "";
                    }

                    if (!line.trim().isEmpty()) {
                        block.append(line);
                        block.append('\n');

                    } else if (!block.isEmpty()) {
                        String blockString = rtrim(block.toString());
                        
                        int pos = 0;
                        while (pos < blockString.length() && blockString.charAt(pos) == '#') {
                            pos++;
                        }

                        if (pos > 0 && pos < blockString.length() && Character.isWhitespace(blockString.charAt(pos))) {
                            structure.add(new Header(
                                List.of(new Text(parseInlineMarkdown(blockString.substring(pos + 1).trim()))),
                                pos
                            ));
                        } else {
                            structure.add(new HtmlParagraph(
                                List.of(new Text(parseInlineMarkdown(rtrim(blockString))))
                            ));
                        }

                        block = new StringBuilder();
                    }
                }
            } catch (ReflectiveOperationException e) {
                System.out.println("Java reflection error: " + e.getMessage());
            } finally {
                input.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open input file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot read input file: " + e.getMessage());
        }


        System.out.println(structure);


        // Output:
        try {
            BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf-8"
                )
            );

            try {
                StringBuilder result = new StringBuilder();
                for (AbstractElement block : structure) {
                    block.toHtml(result);
                    result.append('\n');
                }
                output.write(result.toString());

            } finally {
                output.close();
            }
        } catch (IOException e) {
            System.out.println("Cannot write to output file: " + e.getMessage());
        }
    }
}