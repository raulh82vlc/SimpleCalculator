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

/**
 * Validator of inputs and Strings transformations
 * @author Raul Hernandez Lopez.
 */

public final class CharactersValidator {

    public static final char PLUS_CHAR = '+';
    public static final char MINUS_CHAR = '-';
    public static final char SPACE_CHAR = ' ';
    public static final char ZERO_CHAR = '0';
    public static final char DOT_CHAR = '.';

    public CharactersValidator() {

    }

    public char transformSign(char latestCharacter, char currentCharacter) {
        switch (latestCharacter) {
            case PLUS_CHAR:
                if (currentCharacter == PLUS_CHAR) {
                    return PLUS_CHAR;
                } else {
                    return MINUS_CHAR;
                }
            case MINUS_CHAR:
                if (currentCharacter == MINUS_CHAR) {
                    return PLUS_CHAR;
                } else {
                    return MINUS_CHAR;
                }
            default:
                return currentCharacter;
        }
    }

    public char transformDecimalNotation(char latestCharacter, char digit) {
        switch (latestCharacter) {
            case DOT_CHAR:
                if (digit != DOT_CHAR) {
                    return digit;
                } else {
                    return DOT_CHAR;
                }
            case PLUS_CHAR:
            case MINUS_CHAR:
                return ZERO_CHAR;
            default:
                return digit;
        }
    }
}
