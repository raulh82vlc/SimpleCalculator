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

package com.raulh82vlc.BasicCalculator.domain.interactors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * Operations strategy unit tests for computing individual addition or substraction
 *  as well as computing more complex calculations
 * @author Raul Hernandez Lopez.
 */
public class OperationsStrategyImplTest {

    private static final String TWO_DECIMALS = "%.2f";
    private OperationsStrategyImpl operationsStrategy;
    private Queue<String> operators;
    private Queue<Float> values;

    @Before
    public void setUp() {
        operationsStrategy = new OperationsStrategyImpl();
        operators = new LinkedList<>();
        values = new LinkedList<>();
    }

    @After
    public void tearDown() {
        operationsStrategy = null;
        operators.clear();
        values.clear();
    }

    @Test
    public void computeAddition() throws Exception {
        assertEquals(String.format(Locale.UK, TWO_DECIMALS, 6.1f),
                String.format(Locale.UK, TWO_DECIMALS, operationsStrategy.computeAddition(5.0f, 1.1f)));
    }

    @Test
    public void computeSubsctraction() throws Exception {
        assertEquals(String.format(Locale.UK, TWO_DECIMALS, -1.9f),
                String.format(Locale.UK, TWO_DECIMALS, operationsStrategy.computeSubsctraction(1.2f, 3.1f)));
    }

    @Test
    public void computeOperationsNoOperators() throws Exception {
        values.add(5.456f);
        float result = operationsStrategy.computeOperations(values, operators);
        assertEquals(String.format(Locale.UK, TWO_DECIMALS, 5.456f),
                String.format(Locale.UK, TWO_DECIMALS, result));
    }

    @Test
    public void computeOperations() throws Exception {
        values.add(0f);
        operators.add("-");
        values.add(5.456f);
        operators.add("+");
        values.add(7.6f);
        operators.add("-");
        values.add(10.89f);
        operators.add("+");
        values.add(100f);
        operators.add("-");
        values.add(200f);
        float result = operationsStrategy.computeResult(values, operators);
        assertEquals(String.format(Locale.UK, TWO_DECIMALS, -108.75f),
                String.format(Locale.UK, TWO_DECIMALS, result));
    }

    @Test
    public void computeOperationsSimpleSubstraction() throws Exception {
        values.add(5f);
        operators.add("-");
        values.add(2.5f);
        float result = operationsStrategy.computeResult(values, operators);
        assertEquals(String.format(Locale.UK, TWO_DECIMALS, 2.5f),
                String.format(Locale.UK, TWO_DECIMALS, result));
    }

    @Test
    public void computeOperationsSimpleAddition() throws Exception {
        values.add(5f);
        operators.add("+");
        values.add(100f);
        float result = operationsStrategy.computeResult(values, operators);
        assertEquals(String.format(Locale.UK, TWO_DECIMALS, 105f),
                String.format(Locale.UK, TWO_DECIMALS, result));
    }
}