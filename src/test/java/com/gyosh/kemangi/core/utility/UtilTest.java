package com.gyosh.kemangi.core.utility;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UtilTest {
    @Test
    public void testReplaceNonAsciiWithSpace() {
        assertEquals("Should work for empty String", "", Util.replaceNonAsciiWithSpace(""));

        assertEquals(
                "Should remove non-ASCII characters",
                "some  ppeared  os",
                Util.replaceNonAsciiWithSpace("some 阿ppeared ¢os")
        );
    }

    @Test
    public void testLoadAndSanitizeDocument() {
        List<List<String>> doc = Util.loadAndSanitizeDocument("src/test/resources/test-input.txt");

        List<List<String>> expectedDoc = new ArrayList<List<String>>();
        expectedDoc.add(Arrays.asList("some", "ppeared", "os"));
        expectedDoc.add(Arrays.asList("with", "annoying", "spaces"));
        expectedDoc.add(Arrays.asList("wow", "much"));
        expectedDoc.add(new ArrayList<String>());
        expectedDoc.add(Arrays.asList("senpai", "noticed", "me", "(", ")", "yay"));

        for (int i = 0; i < doc.size(); i++) {
            List<String> tokens = doc.get(i);
            List<String> expectedTokens = expectedDoc.get(i);

            assertEquals("Should have the same length for line " + i, expectedTokens.size(), tokens.size());

            for (int j = 0; j < tokens.size(); j++) {
                assertEquals("Should be equal for token " + j + " in line " + i, expectedTokens.get(j), tokens.get(j));
            }
        }
    }

    @Test
    public void testReadList() {
        List<String> list = Util.readList("src/test/resources/test-stop-word-list.txt");
        List<String> expectedList = Arrays.asList("rp", "koq", "nih", "http://.*", "#.*");

        assertEquals("Should have the same length", list.size(), expectedList.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals("Should be equal for token " + i, expectedList.get(i), list.get(i));
        }
    }

    @Test
    public void testJoin() {
        List<String> emptyList = new ArrayList<String>();
        assertEquals("Should work for empty List", "", Util.join(emptyList, ","));

        List<String> list = Arrays.asList("a", "b", "c");
        assertEquals("Should work for List", "a,b,c", Util.join(list, ","));
        assertEquals("Should work for List", "a-b-c", Util.join(list, "-"));
        assertEquals("Should work for List", "a==b==c", Util.join(list, "=="));
        assertEquals("Should work for List", "a\nb\nc", Util.join(list, "\n"));
    }

    @Test
    public void testClone() {
        List<List<String>> doc = new ArrayList<List<String>>();
        doc.add(Arrays.asList("some", "ppeared", "os"));
        doc.add(Arrays.asList("with", "annoying", "spaces"));
        doc.add(Arrays.asList("wow", "much"));
        doc.add(new ArrayList<String>());
        doc.add(Arrays.asList("senpai", "noticed", "me", "(", ")", "yay"));

        List<List<String>> docClone = Util.clone(doc);

        assertNotSame("Should not be the same list of list of String", doc, docClone);
        assertEquals("Should have the same length", doc.size(), docClone.size());
        for (int i = 0; i < doc.size(); i++) {
            List<String> tokens = doc.get(i);
            List<String> tokensClone = docClone.get(i);

            assertNotSame("Should not be the same list of String", tokens, tokensClone);
            assertEquals("Should have the same length for line " + i, tokens.size(), tokensClone.size());

            for (int j = 0; j < tokens.size(); j++) {
                assertEquals("Should be equal for token " + j + " in line " + i, tokens.get(j), tokensClone.get(j));
            }
        }
    }
}