package com.gyosh.kemangi.core.task;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StopWordRemovalTest {
    private Task stopWordRemoval;
    private List<List<String>> doc;

    @Before
    public void setUp() throws Exception {
        stopWordRemoval = new StopWordRemoval();

        doc = new ArrayList<List<String>>();
        doc.add(Arrays.asList("Pak", "kepala", "desa", "tidak", "tahu", "bahwa", "3", "pencuri", "di", "rumah", "itu", "adalah", "teman", "lamanya!"));
    }

    @Test
    public void testExec() throws Exception {
        List<List<String>> result = stopWordRemoval.exec(doc);

        List<List<String>> expectedDoc = new ArrayList<List<String>>();
        expectedDoc.add(Arrays.asList("pak", "kepala", "desa", "tahu", "3", "pencuri", "rumah", "teman"));

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