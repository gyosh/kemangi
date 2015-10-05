package com.gyosh.kemangi.core.task;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OwnStopWordRemovalTest {
    private Task ownStopWordRemoval;
    private List<List<String>> doc;

    @Before
    public void setUp() throws Exception {
        ownStopWordRemoval = new OwnStopWordRemoval("src/test/resources/test-stop-word-list.txt");

        doc = new ArrayList<List<String>>();
        doc.add(Arrays.asList("harga", "cabai", "Rp", "15.000,00"));
        doc.add(Arrays.asList("harga", "cabai", "rp", "15.000,00"));
        doc.add(Arrays.asList("bbm", "koq", "naik,", "warga", "sedih", "#edisicurhat"));
        doc.add(Arrays.asList("website", "http://www.lucu.com", "diblokir", "menko"));
    }

    @Test
    public void testExec() throws Exception {
        List<List<String>> result = ownStopWordRemoval.exec(doc);

        List<List<String>> expectedDoc = new ArrayList<List<String>>();
        expectedDoc.add(Arrays.asList("harga", "cabai", "Rp", "15.000,00"));
        expectedDoc.add(Arrays.asList("harga", "cabai", "15.000,00"));
        expectedDoc.add(Arrays.asList("bbm", "naik,", "warga", "sedih"));
        expectedDoc.add(Arrays.asList("website", "diblokir", "menko"));

        for (int i = 0; i < result.size(); i++) {
            List<String> tokens = result.get(i);
            List<String> expectedTokens = expectedDoc.get(i);

            assertEquals("Should have the same length for line " + i, expectedTokens.size(), tokens.size());

            for (int j = 0; j < tokens.size(); j++) {
                assertEquals("Should be equal for token " + j + " in line " + i, expectedTokens.get(j), tokens.get(j));
            }
        }
    }
}