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

package com.raulh82vlc.BasicCalculator.di.components;

import com.raulh82vlc.BasicCalculator.di.modules.ActivityModule;
import com.raulh82vlc.BasicCalculator.di.modules.CalculationsModule;
import com.raulh82vlc.BasicCalculator.di.scopes.ActivityScope;
import com.raulh82vlc.BasicCalculator.ui.calculator.CalculatorActivity;
import com.raulh82vlc.BasicCalculator.ui.calculator.CalculatorFragment;

import dagger.Component;

/**
 * ComicsListComponent is the main container of dependencies
 * linked to the activity context. Moreover, this component extends
 * {@link AbstractActivityComponent}, therefore the activity context
 * is provided from the abstract parent component.
 *
 * @author Raul Hernandez Lopez
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {
                ActivityModule.class,
                CalculationsModule.class
        })
public interface CalculatorComponent extends AbstractActivityComponent {
    void inject(CalculatorActivity calculatorActivity);

    void inject(CalculatorFragment calculatorFragment);
}
