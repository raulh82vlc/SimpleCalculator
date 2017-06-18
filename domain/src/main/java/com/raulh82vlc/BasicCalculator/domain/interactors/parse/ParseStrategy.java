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

import java.util.Queue;

/**
 *
 * Parse strategy to parse screen information into understandable values
 *
 * @author Raul Hernandez Lopez.
 */
public interface ParseStrategy {
    /**
     * Parse a String input into tokens
     * @param input String input
     * @return List of String tokens
     */
    void parseInputToTokens(String input, Queue<Float> values, Queue<String> operations);

    /**
     * Compute to final result by means of the list of operations and values
     * @param token
     * @return
     */
    void parseTokensToValuesAndOperations(int i, String token, Queue<Float> values, Queue<String> operations);
}
