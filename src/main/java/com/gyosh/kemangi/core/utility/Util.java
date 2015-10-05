package com.gyosh.kemangi.core.utility;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
    private static final Logger logger = Logger.getLogger(Util.class);

    public static String replaceNonAsciiWithSpace(String line) {
        return line.replaceAll("[^\\p{ASCII}]", " ");
    }

    public static List<List<String>> loadAndSanitizeDocument(String filename) {
        List<List<String>> doc = new ArrayList<List<String>>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line = br.readLine();
            while (line != null) {
                String sanitizedString = replaceNonAsciiWithSpace(line).trim();

                List<String> tokens;
                if (sanitizedString.length() > 0) {
                    tokens = Arrays.asList(sanitizedString.split("\\s+"));
                } else {
                    tokens = new ArrayList<String>();
                }

                doc.add(tokens);
                line = br.readLine();
            }
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }

        return doc;
    }

    public static List<String> readList(String filename) {
        List<String> list = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line = br.readLine();
            while (line != null) {
                list.add(line);

                line = br.readLine();
            }
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }

        return list;
    }

    public static String join(List<String> list, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuilder.append(delimiter);
            }
            stringBuilder.append(list.get(i));
        }

        return stringBuilder.toString();
    }

    public static String getResourceContent(String fileName){
        String result = "";
        ClassLoader classLoader = Util.class.getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    public static void exportDocument(String outputPath, List<List<String>> doc) {
        try {
            FileWriter fw = new FileWriter(outputPath);
            BufferedWriter bw = new BufferedWriter(fw);

            for (List<String> tokens : doc) {
                for (int i = 0; i < tokens.size(); i++) {
                    bw.write(tokens.get(i));
                    if (i + 1 < tokens.size()) {
                        bw.write(" ");
                    }
                }
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static List<List<String>> clone(List<List<String>> doc) {
        List<List<String>> docClone = new ArrayList<List<String>>();
        for (List<String> tokens : doc) {
            List<String> tokensClone = new ArrayList<String>();
            for (String token : tokens) {
                tokensClone.add(token);
            }
            docClone.add(tokensClone);
        }
        return docClone;
    }
}
