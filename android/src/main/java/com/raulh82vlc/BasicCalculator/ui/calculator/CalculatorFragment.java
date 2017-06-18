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

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raulh82vlc.BasicCalculator.R;
import com.raulh82vlc.BasicCalculator.ui.common.BaseFragment;
import com.raulh82vlc.BasicCalculator.ui.presentation.CalculatorPresenter;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <p>Calculator Fragment which uses all injected views or components</p>
 * <p>first of all when the activity is created the component,
 * as well as presenter and view injections for each UI element</p>
 *
 * @author Raul Hernandez Lopez
 */
public class CalculatorFragment extends BaseFragment implements
        CalculatorPresenter.View {

    /**
     * UI injections
     */
    @InjectView(R.id.calculator_screen_tv)
    public TextView screenResults;

    /**
     * DI
     */
    @Inject
    CalculatorPresenter calculatorPresenter;

    // UI Widgets
    private CalculatorActivity activity;

    @OnClick(R.id.zero_btn)
    public void onZero() {
        calculatorPresenter.addDigit(getString(R.string.zero_char).charAt(0));
    }

    @OnClick(R.id.one_btn)
    public void onOne() {
        calculatorPresenter.addDigit(getString(R.string.one_char).charAt(0));
    }

    @OnClick(R.id.two_btn)
    public void onTwo() {
        calculatorPresenter.addDigit(getString(R.string.two_char).charAt(0));
    }

    @OnClick(R.id.three_btn)
    public void onThree() {
        calculatorPresenter.addDigit(getString(R.string.three_char).charAt(0));
    }

    @OnClick(R.id.four_btn)
    public void onFour() {
        calculatorPresenter.addDigit(getString(R.string.four_char).charAt(0));
    }

    @OnClick(R.id.five_btn)
    public void onFive() {
        calculatorPresenter.addDigit(getString(R.string.five_char).charAt(0));
    }

    @OnClick(R.id.six_btn)
    public void onSix() {
        calculatorPresenter.addDigit(getString(R.string.six_char).charAt(0));
    }

    @OnClick(R.id.seven_btn)
    public void onSeven() {
        calculatorPresenter.addDigit(getString(R.string.seven_char).charAt(0));
    }

    @OnClick(R.id.eight_btn)
    public void onEight() {
        calculatorPresenter.addDigit(getString(R.string.eight_char).charAt(0));
    }

    @OnClick(R.id.nine_btn)
    public void onNine() {
        calculatorPresenter.addDigit(getString(R.string.nine_char).charAt(0));
    }

    @OnClick(R.id.dot_btn)
    public void onDot() {
        calculatorPresenter.addDot(getString(R.string.dot_char).charAt(0));
    }

    @OnClick(R.id.plus_btn)
    public void onPlus() {
        calculatorPresenter.addCharacter(getString(R.string.plus_char).charAt(0));
    }

    @OnClick(R.id.minus_btn)
    public void onMinus() {
        calculatorPresenter.addCharacter(getString(R.string.minus_char).charAt(0));
    }

    @OnClick(R.id.equals_btn)
    public void onEquals() {
        calculatorPresenter.readScreenWithOperations(getCurrentScreen());
    }

    @OnClick(R.id.remove_btn)
    public void onRemove() {
        calculatorPresenter.removeChar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (CalculatorActivity) context;
    }

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity.component().inject(this);
        calculatorPresenter.setView(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        calculatorPresenter.resetView();
        super.onDestroyView();
    }

    @Override
    public void hideLoader() {
        activity.hideLoader();
    }

    @Override
    public void showLoader() {
        activity.showLoaderWithTitleAndDescription("", getString(R.string.loading));
    }

    @Override
    public void showComputingResult() {
        activity.showLoaderWithTitleAndDescription("", getString(R.string.computing_result));
    }

    @Override
    public boolean isReady() {
        return isAdded();
    }

    @Override
    public void showResult(String operationsCalculated) {
        screenResults.setText(operationsCalculated);
    }

    @Override
    public char getLatestCharacter() {
        String chain = screenResults.getText().toString();
        if (chain.length() > 0) {
            int lastIndex = chain.length() - 1;
            return chain.charAt(lastIndex);
        } else {
            return ' ';
        }
    }

    @Override
    public String getCurrentScreen() {
        return screenResults.getText().toString();
    }

    @Override
    public void showLineFormattedOnScreen(String newScreenResults) {
        screenResults.setText(newScreenResults);
    }
}
