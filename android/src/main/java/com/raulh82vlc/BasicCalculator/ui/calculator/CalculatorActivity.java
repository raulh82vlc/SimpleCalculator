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

package com.raulh82vlc.BasicCalculator.ui.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.raulh82vlc.BasicCalculator.BasicCalculatorApp;
import com.raulh82vlc.BasicCalculator.R;
import com.raulh82vlc.BasicCalculator.di.components.DaggerCalculatorComponent;
import com.raulh82vlc.BasicCalculator.di.components.CalculatorComponent;
import com.raulh82vlc.BasicCalculator.di.modules.ActivityModule;
import com.raulh82vlc.BasicCalculator.ui.common.BaseActivity;

/**
 * <p>Calculator Activity</p>
 *
 * @author Raul Hernandez Lopez
 */
public class CalculatorActivity extends BaseActivity {

    private CalculatorComponent calculatorComponent;

    public CalculatorComponent component() {
        if (calculatorComponent == null) {
            calculatorComponent = DaggerCalculatorComponent.builder()
                    .applicationComponent(((BasicCalculatorApp) getApplication()).component())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return calculatorComponent;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component().inject(this);
        setToolbarInitialisation();
        setInitialTitle();
    }

    @Override
    protected void setInitialTitle() {
        toolbar.setTitle(getString(R.string.app_name));
    }

    @Override
    protected void setToolbarInitialisation() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calculator;
    }

    @Override
    protected CalculatorActivity getActivity() {
        return this;
    }
}
