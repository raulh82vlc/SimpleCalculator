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

import android.support.annotation.NonNull;

import javax.inject.Inject;

import static com.raulh82vlc.BasicCalculator.ui.presentation.CharactersValidator.DOT_CHAR;
import static com.raulh82vlc.BasicCalculator.ui.presentation.CharactersValidator.MINUS_CHAR;
import static com.raulh82vlc.BasicCalculator.ui.presentation.CharactersValidator.PLUS_CHAR;
import static com.raulh82vlc.BasicCalculator.ui.presentation.CharactersValidator.ZERO_CHAR;

/**
 * String transformator tool
 * @author Raul Hernandez Lopez.
 */
public final class StringTransformator {

    @Inject
    public StringTransformator() {

    }

    @NonNull
    public StringBuilder getStringTransformed(StringBuilder builder, String stringInput, int offset) {
        int limit = stringInput.length() - offset;
        for (int i = 0; i < limit; i++) {
            builder.append(stringInput.charAt(i));
        }
        return builder;
    }

    public void transformDecimalDot(char transformedChar, StringBuilder builder) {
        if (transformedChar == ZERO_CHAR) {
            builder.append(ZERO_CHAR);
            builder.append(DOT_CHAR);
        } else {
            builder.append(transformedChar);
        }
    }

    @NonNull
    public StringBuilder addDot(CharactersValidator validator, char dot, char lastChar, String input) {
        StringBuilder builder = new StringBuilder();
        if (lastChar != DOT_CHAR) {
            getStringTransformed(builder, input, 0);
        } else {
            getStringTransformed(builder, input, 1);
        }
        char transformedChar = validator.transformDecimalNotation(lastChar, dot);
        transformDecimalDot(transformedChar, builder);
        return builder;
    }

    @NonNull
    public StringBuilder addCharacter(CharactersValidator validator, char character, char lastChar, String screen) {
        StringBuilder builder = new StringBuilder();
        if (lastChar == PLUS_CHAR || lastChar == MINUS_CHAR) {
            getStringTransformed(builder, screen, 1);
        } else {
            getStringTransformed(builder, screen, 0);
        }

        char transformedChar = validator.transformSign(lastChar, character);
        builder.append(transformedChar);
        return builder;
    }
}
