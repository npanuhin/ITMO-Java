package md2html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedWriter;

import markup.*;


public class Md2Html {

    private static String rtrim(String s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        return s.substring(0, i + 1);
    }

    private static String[] html = new String[]{"em", "em", "strong", "strong", "s", "code"};
    private static String[] markdownTag = new String[]{"*", "_", "**", "__", "--", "`"};
    private static int tagCount = markdownTag.length;
    private static Map<String, Integer> tagIndexes = new HashMap<>();

    static {
        for (int i = 0; i < tagCount; i++) {
            tagIndexes.put(markdownTag[i], i);
        }
    }

    private static int getTagIndex(String cur) {
        int res = tagIndexes.getOrDefault(cur, -1);
        if (res == -1) {
            res = tagIndexes.getOrDefault(Character.toString(cur.charAt(0)), -1);
        }
        return res;
    }

    private static String parseInlineMarkdown(String text) {
        StringBuilder result = new StringBuilder();

        int[] tagsInText = new int[tagCount];
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\\') {
                i++;
                continue;
            }
            int tagIndex = getTagIndex(text.substring(i, Math.min(i + 2, text.length())));
            if (tagIndex != -1) {
                tagsInText[tagIndex]++;
            }
        }

        System.err.println("aaaaaaaaaaaa");

        for (int i = 0; i < tagCount; ++i) {
            if (tagsInText[i] % 2 == 1) {
                tagsInText[i]--;
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
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
                case '\\':
                    if (i + 1 < text.length()) {
                        result.append(text.charAt(++i));
                    }
                    continue;
            }

            int tagIndex = getTagIndex(text.substring(i, Math.min(i + 2, text.length())));
            if (tagIndex == -1 || tagsInText[tagIndex] == 0) {
                result.append(c);

            } else {
                result.append('<').append(tagsInText[tagIndex] % 2 == 1 ? '/' : "").append(html[tagIndex]).append('>');

                tagsInText[tagIndex]--;
                i += markdownTag[tagIndex].length() - 1;
            }
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
            } finally {
                input.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open input file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot read input file: " + e.getMessage());
        }


        // System.out.println(structure);


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