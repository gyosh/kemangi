package com.gyosh.kemangi.core.task;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NonAlphaNumericRemovalTest {
    private Task nonAlphaNumericRemoval;
    private List<List<String>> doc;

    @Before
    public void setUp() throws Exception {
        nonAlphaNumericRemoval = new NonAlphaNumericRemoval();

        doc = new ArrayList<List<String>>();
        doc.add(Arrays.asList("single.big.chunk"));
        doc.add(Arrays.asList("how", "about", "c.h.u.k.s", "in", "middle"));
        doc.add(Arrays.asList("or", "ending", "c.h.u.k.s"));
        doc.add(Arrays.asList("c.h.u.k.s", "on", "top"));
        doc.add(Arrays.asList("ka", "?!>!", "boom"));
        doc.add(Arrays.asList("qwertyuiopasdfghjklzxcvbnm", "1234567890"));
    }

    @Test
    public void testExec() throws Exception {
        List<List<String>> result = nonAlphaNumericRemoval.exec(doc);

        List<List<String>> expectedDoc = new ArrayList<List<String>>();
        expectedDoc.add(Arrays.asList("single", "big", "chunk"));
        expectedDoc.add(Arrays.asList("how", "about", "c", "h", "u", "k", "s", "in", "middle"));
        expectedDoc.add(Arrays.asList("or", "ending", "c", "h", "u", "k", "s"));
        expectedDoc.add(Arrays.asList("c", "h", "u", "k", "s", "on", "top"));
        expectedDoc.add(Arrays.asList("ka", "boom"));
        expectedDoc.add(Arrays.asList("qwertyuiopasdfghjklzxcvbnm", "1234567890"));

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