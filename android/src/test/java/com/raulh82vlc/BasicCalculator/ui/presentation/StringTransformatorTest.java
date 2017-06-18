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
 * String tranformator tools
 * @author Raul Hernandez Lopez.
 */
public class StringTransformatorTest {

    private StringTransformator underTest;

    @Before
    public void setUp() {
        underTest = new StringTransformator();
    }

    @After
    public void tearDown() {
        underTest = null;
    }

    @Test
    public void getStringTransformed() throws Exception {
        StringBuilder builder = new StringBuilder();
        assertEquals("1+2+3+", underTest.getStringTransformed(builder, "1+2+3+5", 1).toString());
    }

    @Test
    public void transformDecimalDot() throws Exception {
        StringBuilder builder = new StringBuilder();
        underTest.transformDecimalDot('0', builder);
        assertEquals("0.", builder.toString());
    }

    @Test
    public void addDot() throws Exception {
        StringBuilder builder = underTest.addDot(new CharactersValidator(), '.', '7', "27");
        assertEquals("27.", builder.toString());
    }

    @Test
    public void addCharacter() throws Exception {
        StringBuilder builder = underTest.addCharacter(new CharactersValidator(), '-', '+', "12+12+3.4+");
        assertEquals("12+12+3.4-", builder.toString());
    }

}