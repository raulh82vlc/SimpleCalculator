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

import java.util.Queue;

/**
 * Responsible of parsing screen information and validate it
 * as well as calculating results and passing back to the View
 *
 * @author Raul Hernandez Lopez
 */
public interface CalculatorPresenter {

    void setView(View view);

    void resetView();

    void readScreenWithOperations(String currentResult);

    void addCharacter(char character);

    void addDigit(char digit);

    void addDot(char dot);

    void removeChar();

    void computeOperations(Queue<String> operations, Queue<Float> values);

    interface View {

        void showLoader();

        void hideLoader();

        boolean isReady();

        void showResult(String operationsCalculated);

        char getLatestCharacter();

        String getCurrentScreen();

        void showLineFormattedOnScreen(String newScreenResults);

        void showComputingResult();
    }
}
