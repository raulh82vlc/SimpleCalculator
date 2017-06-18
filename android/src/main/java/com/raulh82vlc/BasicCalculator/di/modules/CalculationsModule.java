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

package com.raulh82vlc.BasicCalculator.di.modules;

import com.raulh82vlc.BasicCalculator.di.scopes.ActivityScope;
import com.raulh82vlc.BasicCalculator.domain.interactors.calculate.CalculateOperationsInteractor;
import com.raulh82vlc.BasicCalculator.domain.interactors.calculate.CalculateOperationsInteractorImpl;
import com.raulh82vlc.BasicCalculator.domain.interactors.OperationsStrategy;
import com.raulh82vlc.BasicCalculator.domain.interactors.OperationsStrategyImpl;
import com.raulh82vlc.BasicCalculator.domain.interactors.parse.ParseInputInteractor;
import com.raulh82vlc.BasicCalculator.domain.interactors.parse.ParseInputInteractorImpl;
import com.raulh82vlc.BasicCalculator.domain.interactors.parse.ParseStrategy;
import com.raulh82vlc.BasicCalculator.domain.interactors.parse.ParseStrategyImpl;
import com.raulh82vlc.BasicCalculator.ui.presentation.CalculatorPresenter;
import com.raulh82vlc.BasicCalculator.ui.presentation.CalculatorPresenterImpl;
import com.raulh82vlc.BasicCalculator.ui.presentation.CharactersValidator;
import com.raulh82vlc.BasicCalculator.ui.presentation.StringTransformator;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides all user required artifacts
 * (presenter, interactors)
 * in order to use them in a decoupled way
 *
 * @author Raul Hernandez Lopez
 */
@Module
public class CalculationsModule {

    @Provides
    @ActivityScope
    CalculateOperationsInteractor provideCalculateOperationsInteractor(CalculateOperationsInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @ActivityScope
    ParseInputInteractor provideParseInputInteractor(ParseInputInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @ActivityScope
    CalculatorPresenter provideCalculatorPresenter(CalculatorPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    CharactersValidator provideCharactersValidator() {
        return new CharactersValidator();
    }

    @Provides
    @ActivityScope
    OperationsStrategy provideOperationsStrategy(OperationsStrategyImpl operationsStrategy) {
        return operationsStrategy;
    }

    @Provides
    @ActivityScope
    ParseStrategy provideParseStrategy(ParseStrategyImpl parseStrategy) {
        return parseStrategy;
    }

    @Provides
    @ActivityScope
    StringTransformator provideStringTransformator() {
        return new StringTransformator();
    }
}
