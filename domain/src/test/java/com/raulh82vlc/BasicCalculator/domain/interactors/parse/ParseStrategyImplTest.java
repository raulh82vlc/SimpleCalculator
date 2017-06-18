/*
 * Copyright (C) 2017 Raul Hernandez Lopez @raulh82vlc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raulh82vlc.BasicCalculator.domain.interactors.parse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * Operations strategy unit tests for Parsing values
 * @author Raul Hernandez Lopez.
 */
public class ParseStrategyImplTest {

    private static final double DELTA = 0.0002;
    private ParseStrategy parseStrategy;
    private Queue<Float> values;
    private Queue<String> operations;

    @Before
    public void setUp() {
        parseStrategy = new ParseStrategyImpl();
        values = new LinkedList<>();
        operations = new LinkedList<>();
    }

    @After
    public void tearDown() {
        parseStrategy = null;
        operations.clear();
        values.clear();
    }

    @Test
    public void parseSimpleIndividualInput() throws Exception {
        parseStrategy.parseInputToTokens("5.456", values, operations);
        assertEquals(5.456, values.remove(), DELTA);
    }

    @Test
    public void parseInputToTokensDecomposed() throws Exception {
        parseStrategy.parseInputToTokens("-5.456+7", values, operations);
        assertEquals("-", operations.remove());
        assertEquals(0, values.remove(), DELTA);
        assertEquals(5.456, values.remove(), DELTA);
        assertEquals("+", operations.remove());
        assertEquals(7, values.remove(), DELTA);
    }

    @Test
    public void parseInputToTokensDecomposedMore() throws Exception {
        parseStrategy.parseInputToTokens("-5.456+7-10", values, operations);
        assertEquals("-", operations.remove());
        assertEquals(0, values.remove(), DELTA);
        assertEquals(5.456, values.remove(), DELTA);
        assertEquals("+", operations.remove());
        assertEquals(7, values.remove(), DELTA);
        assertEquals("-", operations.remove());
        assertEquals(10, values.remove(), DELTA);
    }

    @Test
    public void parseInputToTokensDecomposedMoreComplex() throws Exception {
        parseStrategy.parseInputToTokens("-5.456+7.6-10.89", values, operations);
        assertEquals("-", operations.remove());
        assertEquals(0, values.remove(), DELTA);
        assertEquals(5.456, values.remove(), DELTA);
        assertEquals("+", operations.remove());
        assertEquals(7.6, values.remove(), DELTA);
        assertEquals("-", operations.remove());
        assertEquals(10.89, values.remove(), DELTA);
    }

    @Test
    public void parseInputToTokensDecomposedMoreComplex1() throws Exception {
        parseStrategy.parseInputToTokens("-5.456+7.6-10.89+100-200", values, operations);
        assertEquals("-", operations.remove());
        assertEquals(0, values.remove(), DELTA);
        assertEquals(5.456, values.remove(), DELTA);
        assertEquals("+", operations.remove());
        assertEquals(7.6, values.remove(), DELTA);
        assertEquals("-", operations.remove());
        assertEquals(10.89, values.remove(), DELTA);
        assertEquals("+", operations.remove());
        assertEquals(100, values.remove(), DELTA);
        assertEquals("-", operations.remove());
        assertEquals(200, values.remove(), DELTA);
    }
}