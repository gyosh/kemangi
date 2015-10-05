package com.gyosh.kemangi.core.task;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CaseFoldingTest {
    private Task caseFolding;
    private List<List<String>> doc;

    @Before
    public void setUp() throws Exception {
        caseFolding = new CaseFolding();

        doc = new ArrayList<List<String>>();
        doc.add(Arrays.asList("Saya", "bErmain", "Petak", "Umpet"));
        doc.add(Arrays.asList("PAK", "MAU", "LAPOR"));
    }

    @Test
    public void testExec() throws Exception {
        List<List<String>> result = caseFolding.exec(doc);

        List<List<String>> expectedDoc = new ArrayList<List<String>>();
        expectedDoc.add(Arrays.asList("saya", "bermain", "petak", "umpet"));
        expectedDoc.add(Arrays.asList("pak", "mau", "lapor"));

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