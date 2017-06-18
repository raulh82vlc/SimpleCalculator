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

import com.raulh82vlc.BasicCalculator.domain.interactors.calculate.CalculateOperationsInteractor;
import com.raulh82vlc.BasicCalculator.domain.interactors.parse.ParseInputInteractor;
import com.raulh82vlc.BasicCalculator.domain.interactors_response.CalculatorResultCallbackImpl;
import com.raulh82vlc.BasicCalculator.domain.interactors_response.ParseResultCallbackImpl;

import java.util.Queue;

import javax.inject.Inject;

/**
 * Implementation of the {@link CalculatorPresenter}
 *
 * @author Raul Hernandez Lopez
 */
public class CalculatorPresenterImpl implements CalculatorPresenter {

    private final CalculateOperationsInteractor interactorCalculations;
    private final CharactersValidator validator;
    private final StringTransformator transformator;
    private final ParseInputInteractor interactorParseInput;
    private View view;

    @Inject
    CalculatorPresenterImpl(CalculateOperationsInteractor interactorCalculateOperations,
                            CharactersValidator validator,
                            StringTransformator transformator,
                            ParseInputInteractor interactorParseInput) {
        this.interactorCalculations = interactorCalculateOperations;
        this.interactorParseInput = interactorParseInput;
        this.validator = validator;
        this.transformator = transformator;
    }

    @Override
    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("The view should be instantiated");
        }
        this.view = view;
    }

    @Override
    public void resetView() {
        view = null;
    }

    @Override
    public void readScreenWithOperations(String currentResult) {
        if (view.isReady()) {
            view.showLoader();
            interactorParseInput.execute(currentResult, new ParseResultCallbackImpl(view, this));
        }
    }

    @Override
    public void addDot(char dot) {
        if (view.isReady()) {
            char lastChar = view.getLatestCharacter();
            String input = view.getCurrentScreen();
            StringBuilder builder = transformator.addDot(validator, dot, lastChar, input);
            passLineFormatted(builder);
        }
    }

    @Override
    public void removeChar() {
        if (view.isReady()) {
            StringBuilder builder = new StringBuilder();
            String currentScreen = view.getCurrentScreen();
            if (currentScreen.length() == 1) {
                view.showLineFormattedOnScreen("");
            } else {
                transformator.getStringTransformed(builder, currentScreen, 1);
                passLineFormatted(builder);
            }
        }
    }

    @Override
    public void computeOperations(Queue<String> operations, Queue<Float> values) {
        if (view.isReady()) {
            interactorCalculations.execute(operations, values, new CalculatorResultCallbackImpl(view));
        }
    }

    @Override
    public void addCharacter(char character) {
        if (view.isReady()) {
            char lastChar = view.getLatestCharacter();
            String screen = view.getCurrentScreen();
            passLineFormatted(transformator.addCharacter(validator, character, lastChar, screen));
        }
    }

    @Override
    public void addDigit(char digit) {
        if (view.isReady()) {
            view.showLineFormattedOnScreen(view.getCurrentScreen() + digit);
        }
    }

    private void passLineFormatted(StringBuilder builder) {
        String combinedString = builder.toString();
        if (!combinedString.isEmpty()) {
            view.showLineFormattedOnScreen(combinedString);
        }
    }
}
