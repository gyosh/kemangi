package com.gyosh.worker;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility {
    private static final Logger logger = Logger.getLogger(Utility.class);

    public static String removeNonAscii(String line) {
        return line.replaceAll("[^\\p{ASCII}]", " ");
    }

    public static List<List<String>> loadAndSanitizeDocument(String filename) {
        List<List<String>> doc = new ArrayList<List<String>>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line = br.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(removeNonAscii(line).trim().split("\\s+"));
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
}
