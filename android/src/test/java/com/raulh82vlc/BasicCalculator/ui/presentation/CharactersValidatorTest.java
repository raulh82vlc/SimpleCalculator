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

package com.raulh82vlc.BasicCalculator.ui.presentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Characters validator tests
 * @author Raul Hernandez Lopez.
 */
public class CharactersValidatorTest {


    private CharactersValidator validator;

    @Before
    public void setUp() {
        validator = new CharactersValidator();
    }

    @After
    public void tearDown() {
        validator = null;
    }

    @Test
    public void transformSignTwoNegativesToPositive() throws Exception {
        assertEquals('+', validator.transformSign('-', '-'));
    }

    @Test
    public void transformSignTwoPositiveNothing() throws Exception {
        assertEquals('+', validator.transformSign('+', '+'));
    }

    @Test
    public void transformSignPositiveNegativeToNegative() throws Exception {
        assertEquals('-', validator.transformSign('+', '-'));
    }

    @Test
    public void transformSignPlusAndNumber() throws Exception {
        assertEquals('+', validator.transformSign('9', '+'));
    }

    @Test
    public void transformPositiveToPositive() throws Exception {
        assertEquals('-', validator.transformSign('+', '-'));
    }

    @Test
    public void transformDecimalNotation() throws Exception {
        assertEquals('.', validator.transformDecimalNotation('.', '.'));
    }

    @Test
    public void transformDecimalNotationPlusBefore() throws Exception {
        assertEquals('0', validator.transformDecimalNotation('+', '.'));
    }
}