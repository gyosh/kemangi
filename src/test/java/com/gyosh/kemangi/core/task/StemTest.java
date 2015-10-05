package com.gyosh.kemangi.core.task;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StemTest {
    private Task stem;
    private List<List<String>> doc;

    @Before
    public void setUp() throws Exception {
        stem = new Stem();

        doc = new ArrayList<List<String>>();
        doc.add(Arrays.asList("Mempermainkan", "peranan", "12", "domba", "di", "pementasan"));
    }

    @Test
    public void testExec() throws Exception {
        List<List<String>> result = stem.exec(doc);

        List<List<String>> expectedDoc = new ArrayList<List<String>>();
        expectedDoc.add(Arrays.asList("main", "peran", "12", "domba", "di", "pentas"));

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