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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * Operations strategy implementation to calculate all kind of products prices when check out happens
 * @author Raul Hernandez Lopez.
 */

public class ParseStrategyImpl implements ParseStrategy {

    private static final String REGEXP = "(\\+|-)*(\\d+\\.*\\d*)+(\\+|-)*(\\d+\\.*\\d*)*";
    private final Pattern pattern;

    public static final String PLUS_CHAR = "+";
    public static final String MINUS_CHAR = "-";

    @Inject
    public ParseStrategyImpl() {
        pattern = Pattern.compile(REGEXP);
    }

    @Override
    public void parseInputToTokens(String input, Queue<Float> values, Queue<String> operations) {
        Matcher matcher = pattern.matcher(input);
        // only first time value 0 must be placed as a first operand for having a couple
        int j = 0;
        while (matcher.find()) {
            // by default the extracted matches comes from group number 1
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (matcher.group(i) != null && !matcher.group(i).isEmpty()) {
                    parseTokensToValuesAndOperations(j, matcher.group(i), values, operations);
                    j++;
                }
            }
        }
    }

    @Override
    public void parseTokensToValuesAndOperations(int j, String token, Queue<Float> values, Queue<String> operations) {
        switch (token) {
            case PLUS_CHAR:
            case MINUS_CHAR:
                // adding 0 as a first value when nothing before to start with a pair of values
                if (j == 0) {
                    values.add(0f);
                }
                operations.add(token);
                break;
            default:
                values.add(Float.parseFloat(token));
                break;
        }
    }
}
