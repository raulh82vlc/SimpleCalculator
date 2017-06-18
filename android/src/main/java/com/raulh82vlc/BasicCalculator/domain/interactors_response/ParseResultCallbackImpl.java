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

import com.raulh82vlc.BasicCalculator.domain.interactors.parse.ParseInputInteractor;
import com.raulh82vlc.BasicCalculator.ui.presentation.CalculatorPresenter;

import java.util.Queue;

/**
 * Calculator callback, communicating towards its view
 *
 * @author Raul Hernandez Lopez.
 */
public class ParseResultCallbackImpl implements ParseInputInteractor.ParseResultCallback {

    private final CalculatorPresenter.View view;
    private final CalculatorPresenter presenter;

    public ParseResultCallbackImpl(CalculatorPresenter.View view, CalculatorPresenter presenter) {
        this.view = view;
        this.presenter = presenter;

    }

    @Override
    public void onResultParsed(Queue<String> operations, Queue<Float> values) {
        if (view.isReady()) {
            view.hideLoader();
            view.showComputingResult();
            presenter.computeOperations(operations, values);
        }
    }
}
