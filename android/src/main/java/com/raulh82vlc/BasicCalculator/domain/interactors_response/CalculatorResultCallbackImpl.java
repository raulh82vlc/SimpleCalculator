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

package com.raulh82vlc.BasicCalculator.domain.interactors_response;

import com.raulh82vlc.BasicCalculator.domain.interactors.calculate.CalculateOperationsInteractor;
import com.raulh82vlc.BasicCalculator.ui.presentation.CalculatorPresenter;

import java.util.Locale;

/**
 * Calculator result callback, communicating towards its view
 *
 * @author Raul Hernandez Lopez.
 */
public class CalculatorResultCallbackImpl implements CalculateOperationsInteractor.CalculatorResultCallback {

    private static final String TWO_DECIMALS = "%.2f";
    private static final String NO_DECIMALS = "%.0f";
    private final CalculatorPresenter.View view;

    public CalculatorResultCallbackImpl(CalculatorPresenter.View view) {
        this.view = view;
    }


    @Override
    public void onComputedResultOK(float resultComputed) {
        if (view.isReady()) {
            view.hideLoader();
            if (resultComputed % 1 == 0) {
                view.showResult(String.format(Locale.UK, NO_DECIMALS, resultComputed));
            } else {
                view.showResult(String.format(Locale.UK, TWO_DECIMALS, resultComputed));
            }
        }
    }
}
